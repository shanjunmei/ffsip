package com.ffzx.weixin.message;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名: MessageUtil
 * <p>
 * 描述: 消息处理工具类
 * <p>
 * 开发人员： 李淼淼
 * <p>
 * 创建时间：  2017-2-28
 * <p>
 * 发布版本：V1.0
 */
public class MessageUtil {

    // 请求消息类型：文本
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    // 请求消息类型：图片
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    // 请求消息类型：语音
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    // 请求消息类型：视频
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    // 请求消息类型：小视频
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
    // 请求消息类型：地理位置
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    // 请求消息类型：链接
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    // 请求消息类型：事件推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    // 事件类型：subscribe(订阅)
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    // 事件类型：unsubscribe(取消订阅)
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    // 事件类型：scan(用户已关注时的扫描带参数二维码)
    public static final String EVENT_TYPE_SCAN = "scan";
    // 事件类型：LOCATION(上报地理位置)
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    // 事件类型：CLICK(自定义菜单)
    public static final String EVENT_TYPE_CLICK = "CLICK";
    // 响应消息类型：文本
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    // 响应消息类型：图片
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    // 响应消息类型：语音
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    // 响应消息类型：视频
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    // 响应消息类型：音乐
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    // 响应消息类型：图文
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
    private static Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    protected static Map<String, String> parseToMap(InputStream inputStream) {
        try {
            // 将解析结果存储在HashMap中
            Map map = new HashMap();
        /*    StringBuffer out = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = inputStream.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
           String source= out.toString();
            logger.info(source);*/
            // 读取输入流
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
            NodeList list = document.getChildNodes();
            list = list.item(0).getChildNodes();
            // 遍历所有子节点
            convertNodeList(map, list);
            // 释放资源
            inputStream.close();
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void convertNodeList(Map map, NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            String key = node.getNodeName();
            Object value = node.getNodeValue();
            NodeList childNodes = node.getChildNodes();
            if (childNodes != null && childNodes.getLength() > 0) {
                value = childNodes.item(0).getNodeValue();
                // convertNodeList((Map) value, childNodes);
            }
            map.put(key, value);
        }
    }

    public static void main(String args[]) {
        String source = "<xml>\n" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "<FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                "<CreateTime>1351776360</CreateTime>\n" +
                "<MsgType><![CDATA[link]]></MsgType>\n" +
                "<Title><![CDATA[公众平台官网链接]]></Title>\n" +
                "<Description><![CDATA[公众平台官网链接]]></Description>\n" +
                "<Url><![CDATA[url]]></Url>\n" +
                "<MsgId>1234567890123456</MsgId>\n" +
                "</xml> ";
        source = source.replace("\n", "");
        ByteArrayInputStream is = new ByteArrayInputStream(source.getBytes());
        try {
            Map map = parseToMap(is);
            System.out.println(map);
            TextMessage message = new TextMessage();
            message.setContent("test");
            message.setToUserName("te");
            message.setCreateTime(new Date().getTime() + "");
            message.setMsgType(REQ_MESSAGE_TYPE_TEXT);
            message.setTitle("title");
            String msg = messageToXml(message);
            System.out.println(msg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static String messageToXml(TextMessage message) {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext jbc = JAXBContext.newInstance(message.getClass());   //传入要转换成xml的对象类型
            Marshaller mar = jbc.createMarshaller();
            mar.marshal(message, sw);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return sw.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
    }

}