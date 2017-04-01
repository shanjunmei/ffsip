package com.ffzx.weixin.message;

import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.wechat.WechatApiService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;


/**
 * 类名: WxMessageCoreService
 * <p>
 * 描述: 核心服务类
 * <p>
 * 开发人员： 李淼淼
 * <p>
 * 创建时间：  2017-2-28
 * <p>
 * 发布版本：V1.0
 */
@Component
public class WxMessageCoreService {

    private static Logger logger = LoggerFactory.getLogger(WxMessageCoreService.class);


    @Resource
    private ArticlePreEditService articlePreEditService;

    @Resource
    private MemberService memberService;

    @Resource
    private WechatApiService wechatApiService;

    /**
     * 处理微信发来的请求
     *
     * @param inputStream
     * @return xml
     */
    public String processRequest(InputStream inputStream) {
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "未知的消息类型！";
        try {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseToMap(inputStream);
            logger.info("接受到消息：{}", requestMap);
            // 发送方帐号
            String fromUserName = requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");


            Member member = memberService.findByOpenId(fromUserName);
            int state = 0;//0 不动，1 创建， 2 修改
            Map<String, String> userInfo = null;
            if (member == null) {
                state = 1;
                member = new Member();
                member.setWxOpenid(fromUserName);
            } else {
                if ((new Date().getTime() - member.getLastUpdateDate().getTime()) > 24 * 3600 * 1000) {//超过一天未更新则更新，防止每次更新，调用超限
                    state = 2;
                }
            }

            if (state != 0) {
                userInfo = wechatApiService.getWxUserInfo(fromUserName);
                String nickName = userInfo.get("nickname");
                String headPic = userInfo.get("headimgurl");
                String coutry=userInfo.get("country");
                String province=userInfo.get("province");
                String city=userInfo.get("city");

                member.setWxHeadimgurl(headPic);
                member.setWxNickName(nickName);
                member.setWxCountry(coutry);
                member.setWxProvince(province);
                member.setWxCity(city);
                if (state == 1) {
                    memberService.add(member);
                } else if (state == 2) {
                    memberService.updateSelective(member);
                }
            }

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            //恢复消息，调换发送者，接收者
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime() + "");
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            String content = null;
            String title = null;

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                String url = requestMap.get("Content");
                Map<String, String> result = articlePreEditService.tryCreateEditArticle(url, member.getCode());
                content = result.get("url");
                title = result.get("title");
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                respContent = "您发送的是小视频消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                String url = requestMap.get("Url");
                Map<String, String> result = articlePreEditService.tryCreateEditArticle(url, member.getCode());
                content = result.get("url");
                title = result.get("title");

            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                }
            }
            // 设置文本消息的内容
            if (StringUtils.isNotBlank(content)) {

                title = "文章编辑:" + title;
                respContent = "<a href=\"" + content + "\">" + title + "</a>";
                textMessage.setContent(respContent);
                respXml = MessageUtil.messageToXml(textMessage);
            } else {
                respXml = "";
            }
            // 将文本消息对象转换成xml

        } catch (Exception e) {
            logger.info("msg fail", e);
        }
        //  logger.info("回复内容：{}", respXml);
        if (respXml == null) {
            respXml = "";
        }
        return respXml;
    }
}