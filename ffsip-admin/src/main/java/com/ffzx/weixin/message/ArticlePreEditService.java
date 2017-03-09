package com.ffzx.weixin.message;

import com.ffzx.ffsip.model.WxEditArticle;
import com.ffzx.ffsip.service.WxEditArticleService;
import com.ffzx.ffsip.util.CodeGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Administrator on 2017/3/2.
 */
@Service
public class ArticlePreEditService {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private WxEditArticleService wxEditArticleService;

    public String tryCreateEditArticle(String url, String createBy) {
        Document template =getTemplate();
        Elements content= template.select("#baopingPreview_content_container");
       // logger.info("template js_content:"+content.toString());
        String code= CodeGenerator.code();
        Document doc = ArticleCrawler.get(url);
        ArticleCrawler.preProcess(doc);
       // logger.info("doc js_content:"+doc.select("#js_content").html());
        content.html(doc.select("#js_article").html());
        template.select("#baopingPreview_topTitle").html(doc.title());
        template.select("#source_code").val(code);
        WxEditArticle wxEditArticle = new WxEditArticle();
        wxEditArticle.setTitle(doc.title());
        wxEditArticle.setContent(template.toString());
        wxEditArticle.setSourceContent(doc.toString());
        wxEditArticle.setUrl(url);
        wxEditArticle.setCode(code);
        wxEditArticle.setCreateBy(createBy);
        wxEditArticle.setLastUpdateBy(createBy);
        int ret = wxEditArticleService.add(wxEditArticle);
        logger.info("pre edit result :" + ret);
        if (ret > 0) {
            return "http://ffsip.ffzxnet.com/ffsip-admin/OpenApi/editArticle.do?code=" + wxEditArticle.getCode();
        } else {
            return null;
        }
    }

    public InputStream getStream(String path) {
      //  URL current=getClass().getResource(".");
      //  logger.info("current :"+current);
     //   URL root=getClass().getResource("/");
     //   logger.info("root :"+root);
        String fp="/../../" + path;
        URL url=getClass().getResource(fp);
        logger.info("url :"+url);
      //  String file= url.getFile();
      //  logger.info("file :"+file);

        return getClass().getResourceAsStream(fp);
    }

    public Document getTemplate() {
        try {
            return Jsoup.parse(getStream("editTemplate/html/template.html"), "utf8", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
