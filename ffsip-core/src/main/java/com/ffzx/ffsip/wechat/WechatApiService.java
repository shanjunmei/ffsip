package com.ffzx.ffsip.wechat;

import java.util.Map;

/**
 * 
 * @description
 * @author  李淼淼
 * @date 2017年02月10日 上午11:23:33
 *
 */

public interface WechatApiService {

	public static String MSG_URL="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

	boolean sendMsg(String toWxOpenId, String content, String token);

	boolean sendMsg(String toWxopenId, String content);

	/**
	 * 获取token
	 * @return
	 */
	public String getAccessToken();

	/**
	 * 授权，获取用户信息
	 * @param code
	 * @return
	 */
	public Map<String,String> oauth(String code);

	Map<String, String> getWxUserInfo(String openid, String accessToken);

	Map<String, String> getWxUserInfo(String openid);
}

