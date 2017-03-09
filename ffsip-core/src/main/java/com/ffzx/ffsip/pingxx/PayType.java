package com.ffzx.ffsip.pingxx;

/***
 * 支付方式
 * @author Administrator
 *
 */
public enum PayType {
	ALIPAY("ALIPAY","支付宝手机支付"),
	ALIPAY_WAP("ALIPAY_WAP","支付宝手机网页支付"),
	ALIPAY_PC_DIRECT("ALIPAY_PC_DIRECT","支付宝 PC 网页支付"),
	WX("WX","微信支付"),
	WX_PUB("WX_PUB","微信公众账号支付"),
	UPACP_WAP("UPACP_WAP","银联全渠道手机网页支付"),
	UPACP_PC("UPACP_PC","银联 PC 网页支付"),
	UPMP_WAP("UPMP_WAP","(银联手机网页支付"),
	BFB_WAP("BFB_WAP","百度钱包手机网页支付"),
	APPLE_PAY("APPLE_PAY","apple支付"),
	WX_PUB_QR("WX_PUB_QR","微信公众账号扫码支付"),
	YEEPAY_WAP("YEEPAY_WAP","易宝手机网页支付"),
	JDPAY_WAP("JDPAY_WAP","(京东手机网页支付");
	
	
	private String name ;
	private String value;
	
	private PayType(String name , String value) {
		this.setName(name);
		this.setValue(value);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
