package com.ffzx.ffsip.wechat.impl;

import com.ffzx.ffsip.util.DateUtil;
import com.ffzx.ffsip.util.HttpClient;
import com.ffzx.ffsip.util.JsonConverter;
import com.ffzx.ffsip.wechat.WechatApiService;
import com.ffzx.ffsip.wechat.model.Token;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 李淼淼
 * @description
 * @date 2017年02月10日 上午11:25:59
 */
@Service
public class WechatApiServiceImpl implements WechatApiService {

    public static String sns_get_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN"; //"https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";//"https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    public static String get_userinfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public static Token token=new Token();
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String args[]) {
        String secret = System.setProperty("wechat.secret", "eb3424ae9186ce9bd273c0d9428a0ad3");
        String appid = System.setProperty("wechat.appid", "wx27cf3061f904356b");
        WechatApiServiceImpl service = new WechatApiServiceImpl();
        Map auth = service.getWxUserInfo("o0_FswEyZEHOsMx3BBgIrusV8jUI");
        System.out.println(auth);
        String wxpenid = "o32Qyv-U9hCu8Ik0wJXf7w3D6ru0";// jj o32Qyv-U9hCu8Ik0wJXf7w3D6ru0
        // my o32QyvzA8VjE9LhyGxmpWDZwvJr4
        String content = "hello world";
        Date current=new Date();

       Date c= new Date(current.getTime()+7100*1000);

        System.out.println(DateUtil.format(current,"yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(DateUtil.format(c,"yyyy-MM-dd HH:mm:ss.SSS"));
        //String token="-03_ucAgObpx9DpuHnYDykb0fB-sRzgbVEta_9tPgoO3bHxBfNaubckZZLI1BNzvUtxyq1DApiF0XueG6MAw7Q9CCar8VFG-dvrRajHjZ3k5ytQAJdMi9IlSAHukEHL-BOCcAIAHUI";
        // boolean flag=service.sendMsg(wxpenid,content);

        //String token = service.getAccessToken();
        // System.out.println(token);
    }

    /**
     * 发送客服消息
     *
     * @param toWxOpenId
     * @param content
     * @param token
     * @return
     */

    @Override
    public boolean sendMsg(String toWxOpenId, String content, String token) {

        Map<String, Object> msg = new HashMap<>();
        Map<String, String> text = new HashMap<>();
        msg.put("touser", toWxOpenId);
        msg.put("msgtype", "text");
        msg.put("text", text);
        text.put("content", content);
        String payLoad = JsonConverter.toJson(msg);
        String response = HttpClient.post(MSG_URL + token, payLoad);
        Map ret = JsonConverter.from(response, Map.class);
        logger.info("{}", ret);
        return true;
    }

    /**
     * @param toWxopenId
     * @param content
     * @return
     */
    @Override
    public boolean sendMsg(String toWxopenId, String content) {
        return sendMsg(toWxopenId, content, getAccessToken());
    }

    /***
     *获取accessToken 并且缓存
     */
    public String getAccessToken() {
        logger.info("token:{}",token.toString());
        String accessToken = null;
        Date current = new Date();
        synchronized (token) {
            if (token != null&&token.getCreateTime()!=null) {
                boolean flag = (token.getCreateTime().getTime() + 7100 * 1000) > current.getTime();
                logger.info("token not invalid:{}",flag);
                if (flag) {
                    return token.getToken();
                }
            }
            String secret = System.getProperty("wechat.secret");
            String appid = System.getProperty("wechat.appid");
            String get_access_token_url = "https://api.weixin.qq.com/cgi-bin/token?" +
                    "appid=" + appid +
                    "&secret=" + secret + "&" +
                    "grant_type=client_credential";
            try {
                String res = HttpClient.get(get_access_token_url);
                Map result = null;
                //利用从HttpEntity中得到的String生成JsonObject
                result = JsonConverter.from(res, Map.class);
                // long expiresTime = (int) result.get("expires_in") * 1000 - 10000;     //设置超时时间
                accessToken = (String) result.get("access_token");
                if (StringUtils.isNotBlank(accessToken)) {
                    token = new Token();
                    token.setToken(accessToken);
                    token.setCreateTime(current);
                }
            } catch (Exception e) {
                logger.info("get tocken fail", e);
            }
        }
        logger.info("token:{}",token.toString());
        return accessToken;
    }

    /***
     *微信授权
     */
    public Map<String, String> oauth(String code) {
        String secret = System.getProperty("wechat.secret");
        String appid = System.getProperty("wechat.appid");

        String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + appid +
                "&secret=" + secret + "&" +
                "code=" + code + "&grant_type=authorization_code";

        String openid = "";
        String accessToken = "";

        //获取授权access_token以及openid
        try {
            String res = HttpClient.get(get_access_token_url);
            logger.info("oauth result:{}",res);
            Map result = null;
            result = JsonConverter.from(res, Map.class);
            accessToken = (String) result.get("access_token");
            openid = (String) result.get("openid");

        } catch (Exception e) {
            logger.info("auth fail", e);
        }

        Map<String, String> map = getWxUserInfo(openid, accessToken, sns_get_userinfo);

        return map;
    }

    /**
     * 获取微信用户信息
     *
     * @param openid
     * @param accessToken
     * @return
     */
    @Override
    public Map<String, String> getWxUserInfo(String openid, String accessToken) {
        String _user_info_url = get_userinfo;

        return getWxUserInfo(openid, accessToken, _user_info_url);

    }

    public Map<String, String> getWxUserInfo(String openid, String accessToken, String _user_info_url) {
        Map<String, String> map = new HashMap<String, String>();
        //获取用户信息
        try {

            _user_info_url = _user_info_url.replace("ACCESS_TOKEN", accessToken);
            _user_info_url = _user_info_url.replace("OPENID", openid);
            String res = HttpClient.get(_user_info_url);
            Map result = null;
            //利用从HttpEntity中得到的String生成JsonObject
            result = JsonConverter.from(res, Map.class);

            logger.info("user info:{}", result);

            map.put("openid", (String) result.get("openid"));
            map.put("nickname", (String) result.get("nickname"));
            map.put("headimgurl", (String) result.get("headimgurl"));
            // accessToken = (String) result.get("access_token");
        } catch (Exception e) {
            logger.info("auth fail", e);
        }
        return map;
    }

    /**
     * 获取微信用户信息
     *
     * @param openid
     * @return
     */
    @Override
    public Map<String, String> getWxUserInfo(String openid) {
        return getWxUserInfo(openid, getAccessToken());
    }
}

