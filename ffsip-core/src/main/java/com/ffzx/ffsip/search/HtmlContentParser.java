package com.ffzx.ffsip.search;

import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;

/**
 * Created by Administrator on 2017/4/1.
 */
public class HtmlContentParser {

    /**
     * 获取网页中纯文本信息
     *
     * @param html
     * @return
     * @throws Exception
     */
    public static String getText(String html) {
        try {
            StringBean bean = new StringBean();
            bean.setLinks(false);
            bean.setReplaceNonBreakingSpaces(true);
            bean.setCollapse(true);

            // 返回解析后的网页纯文本信息
            Parser parser = Parser.createParser(html, "utf-8");
            parser.visitAllNodesWith(bean);
            parser.reset();
            return bean.getStrings();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
