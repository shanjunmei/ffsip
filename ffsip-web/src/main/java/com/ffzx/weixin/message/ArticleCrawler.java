package com.ffzx.weixin.message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Administrator on 2017/2/28.
 */
public class ArticleCrawler {
    public static String resource="http://ffsip.ffzxnet.com/ffsip-web/OpenApi/resource.do?url=";

    public static Document get(String url) {
        try {
            Document doc = Jsoup.connect(url)
                    .header("Accept", "*/*")
                    // .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    //.header("Referer", "https://www.baidu.com/")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    // .header("charset","utf-8")
                    .header("ContentType", "content=\"text/html; charset=UTF-8\"")
                    .timeout(5000)
                    .get();

            return doc;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getString(String url) {
        Document doc = get(url);
        String body = preProcess(doc);
        return body;
    }


    protected static String preProcess(Document doc) {
        Elements elements = doc.select("img[data-src]");
        for (Element e : elements) {
            String dataSrc = e.attr("data-src");
            e.attr("src", resource+dataSrc);
        }
        doc.select("script").remove();
        doc.select(".rich_media_title").remove();
        doc.select(".rich_media_meta_list").remove();
        doc.select("body").attr("contenteditable", "true");
        return doc.toString();
    }

    public static void main(String args[]) throws Exception {
        String url = "http://mp.weixin.qq.com/s/fE612rbYl9TOkHyPzMAsmA";
        String doc = getString(url);
        // doc=new String(doc.getBytes(),"utf8");
        System.out.println(doc);
    }
}
