package com.ffzx.ffsip.pingxx;

public class PayUtil {
	//Ping++的测试key
	public static String APIKEY = null;
	//应用 ID
	public static String APP_ID = null;
	
	//禁用new方法。
	private PayUtil(){
		
	}
	
	//初始化支付环境
	static{
		try {
			APIKEY = System.getProperty("pingxx.appkey");
			APP_ID =  System.getProperty("pingxx.appid");
		} catch (Exception e) {
			//如果获取失败。则默认为正式环境
			APIKEY = "sk_live_ny7vQzn0X8e7v0BFRskkmIWw";
			APP_ID = "app_CqPmnHjPO88CzvX1";
		}
	}
	
//	private static final Log LOG = LogFactory.getLog(PayUtil.class);
	/**
	 * 
	 * @param level 日志级别：0：debug，1：info，2：error
	 */
	@SuppressWarnings("unused")
	public static void log(int level,String method,String msg){
		if(level == 0 ){
			//LOG.debug(DateUtil.convertDateToStr(DateUtil.getCurDate(),DateUtil.GENERAL_FORMHMS)+":"+method+":debug=" + msg);
		//	System.out.println(DateUtil.convertDateToStr(DateUtil.getCurDate(),DateUtil.GENERAL_FORMHMS)+":"+method+":debug=" + msg);
		}else if(level == 1 ){
			//LOG.info(DateUtil.convertDateToStr(DateUtil.getCurDate(),DateUtil.GENERAL_FORMHMS)+":"+method+":info=" + msg);
			//System.err.println(DateUtil.convertDateToStr(DateUtil.getCurDate(),DateUtil.GENERAL_FORMHMS)+":"+method+":info=" + msg);
		}else if(level == 2 ){
//			System.out.println(DateUtil.convertDateToStr(DateUtil.getCurDate(),DateUtil.GENERAL_FORMHMS)+":"+method+":error=" + msg);
			//LOG.error(DateUtil.convertDateToStr(DateUtil.getCurDate(),DateUtil.GENERAL_FORMHMS)+":"+method+":error=" + msg);
		}
	}
}
