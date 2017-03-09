package com.ffzx.ffsip.pingxx;


import java.util.HashMap;
import java.util.Map;

/***
 * 服务端与 Ping++ 通信时可能发生的错误
 * 
 * @author huangyi
 *
 */
public class PayCode {

	private static Map<String,String> codes= new HashMap<String, String>();
	private static PayCode payCode;
	
	public static PayCode getInstance(){
		if(codes == null){
			payCode = new PayCode();
			payCode.init();
		}
		return payCode;
	}
	
	public void init(){
		codes.put("101","请求错误，传入了不正确的地址，参数或值");
		codes.put("102","Ping++ 服务器出现的异常错误");
		codes.put("103","第三方支付渠道出现的错误导致请求出现错误");
		codes.put("charge_closed", "支付订单已结束，不能进行后续操作");
		codes.put("charge_unexpected_status", "支付发生未知异常");
		codes.put("refund_wait_operation", "退款需要等待用户进一步操作");
		codes.put("refund_refused", "退款失败，被支付渠道拒绝");
		codes.put("refund_retry", "退款失败，需要重新发起退款");
		codes.put("refund_manual_intervention", "退款失败，需要通过线下或转账进行退款");
		codes.put("refund_unexpected_status", "退款发生未知异常");
		codes.put("channel_connection_error", "支付渠道通讯异常");
		codes.put("channel_request_error", "请求支付渠道接口失败");
		codes.put("channel_parse_error", "支付渠道返回意外的数据格式发生的解析错误");
		codes.put("channel_sign_error", "支付渠道返回的数据没有通过签名验证");
	}
	
	public String getMsgByCode(String code){
		return codes.get(code);
	}
}
