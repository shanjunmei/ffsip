package com.ffzx.ffsip.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class SerialCodeGenerator {

    private static Map<String, Map<String, String>> cache = new HashMap<String, Map<String, String>>();

    private static String STATNUM = "0001";


    /**
     * main测试
     *
     * @param args
     */
    public static void main(String[] args) {
        SerialCodeGenerator t = new SerialCodeGenerator();

        for (int i = 0; i < 200; i++) {
            System.out.println(t.getNum("order", 12));
        }
    }

    /**
     * @param style  0:yyyyMMdd,1:yyyyMMddHHmmss,3:yyyyMMddHHmmssSSS
     *                11:13位时间戳,到毫秒, 12位时间戳（移除首位）,10位时间戳，到秒
     * @return
     */
    public static String getTime(int style) {
        if (style <10) {
            String pattern="yyyyMMddHHmmssSSS";
            if(style==0){
                pattern="yyyyMMdd";
            }else if(style==1){
                pattern="yyyyMMddHHmmss";
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            DateFormat df = new SimpleDateFormat(pattern);
            return df.format(cal.getTime());
        } else if (style > 10) {
            Date date = new Date();
            String time = String.valueOf(date.getTime());
            if(style==12){
                time = time.substring(1);
            }else if(style==13){
                time = time.substring(0,time.length()-3);
            }
            return time;
        }
        return "";

    }

    /**
     * 判断序号是否到了最后一个
     *
     * @param s
     * @return
     */
    public static String getLastSeq(String s, int len) {
        String rs = s;
        int i = Integer.parseInt(rs);
        i += 1;
        rs = "" + i;
        for (int j = rs.length(); j < len; j++) {
//rs="0"+rs;
//直接使用StringUtils类的leftPad方法处理补零
            rs = StringUtils.leftPad(rs, j + 1, "0");
        }
        return rs;
    }

    /**
     * 产生不重复的号码 加锁
     *
     * @return
     */
    public synchronized String getNum(String type, int style) {
        String yearAMon = getTime(style);
        Map<String, String> map = cache.get(type);
        if (map == null) {
            map = new HashMap<>();
            cache.put(type, map);
        }
        String last6Num = map.get(yearAMon);
        if (last6Num == null) {
            String maxSeq = null;
            if ("order".equals(type)) {
                //maxSeq = orderInfoService.findMaxSeq();
            } else if ("game".equals(type)) {
                //maxSeq = gameService.findMaxSeq();
            }
            if (StringUtils.isNotBlank(maxSeq)) {
                map.put(yearAMon, maxSeq);
            } else {
                map.put(yearAMon, STATNUM);
            }

        } else {
            map.put(yearAMon, getLastSeq(last6Num, STATNUM.length()));
        }

        return yearAMon + map.get(yearAMon);
    }

}