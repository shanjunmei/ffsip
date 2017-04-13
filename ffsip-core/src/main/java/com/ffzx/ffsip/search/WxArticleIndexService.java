package com.ffzx.ffsip.search;

import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.service.WxArticleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */
@Component
public class WxArticleIndexService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private WxArticleService wxArticleService;

    @Resource
    private IndexService indexService;


    public int buildIndex() {
        List<WxArticle> articles = wxArticleService.selectByExample(null);
        try {

            // IndexWriter writer = getIndexWriter();
            for (WxArticle article : articles) {
                indexService.removeIndex("article",article.getId());
                wxArticleService.convertPublisher(article);
                indexService.buildIndex(convert(article), "article");
            }
          /*  writer.flush();
            writer.close();*/
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return articles.size();
    }

    public void buildIndex(WxArticle article) {
        try {
            indexService.removeIndex("article",article.getId());
            indexService.buildIndex(convert(article), "article");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Document convert(WxArticle article) {
        article.setContent(HtmlContentParser.getText(article.getContent()));
        return indexService.convert(article);
    }


/*    public void buildIndex(Document doc, IndexWriter writer) {
        try {
            writer.addDocument(doc);
        } catch (Exception e) {
            logger.info("", e);
        }
    }*/

   /* public IndexWriter getIndexWriter() {
        try {
            Directory directory = indexService.getIndexDirectory("article");

            IndexWriterConfig config = new IndexWriterConfig();
            return new IndexWriter(directory, config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/



   /* public Document convert(WxArticle article) {
        Document doc = new Document();
        FieldType storetype = new FieldType();
        storetype.setStored(true);

        FieldType indextype = new FieldType();
        indextype.setStored(true);
        indextype.setIndexOptions(IndexOptions.DOCS);

        doc.add(new Field("id", article.getId(), indextype));
        doc.add(new Field("code", article.getCode(), indextype));
        doc.add(new Field("title", article.getTitle(), indextype));
        doc.add(new Field("content", HtmlContentParser.getText(article.getContent()), indextype));
        if (StringUtils.isNotBlank(article.getLabel())) {
            doc.add(new Field("label", article.getLabel(), indextype));
        }
        doc.add(new Field("publisher", article.getPublisher(), indextype));
        if (StringUtils.isNotBlank(article.getCoverImg())) {
            doc.add(new Field("coverImg", article.getCoverImg(), storetype));
        }
        if(article.getReadingNum()!=null){
            doc.add(new Field("readingNum", article.getReadingNum().toString(), storetype));
        }
        if(article.getForwardingNum()!=null){
            doc.add(new Field("forwardingNum", article.getForwardingNum().toString(), storetype));
        }
        if(article.getCommentNum()!=null){
            doc.add(new Field("commentNum", article.getCommentNum().toString(), storetype));
        }
        if(article.getLikeNum()!=null){
            doc.add(new Field("likeNum", article.getLikeNum().toString(), storetype));
        }
        doc.add(new Field("createDate", DateUtil.format(article.getCreateDate()), storetype));
        return doc;
    }*/

    public List<WxArticle> query(String[] name, String queryKeys, int pageIndex, int pageSize) {
        List<WxArticle> list = new ArrayList<>();
        WxArticle wxArticle = null;
        try {
            IndexSearcher searcher = indexService.getIndexSearcher("article");

            QueryParser queryParser = new MultiFieldQueryParser(name, new StandardAnalyzer());
            Query query = queryParser.parse(queryKeys);

            Highlighter highlighter = indexService.getHighlighter(query);


            TopDocs tds = searcher.search(query, 500);
            int total = tds.totalHits;
            PageHolder.setTotal(total);
            //注意 此处把500条数据放在内存里。
            ScoreDoc[] sds = tds.scoreDocs;
            int start = (pageIndex - 1) * pageSize;
            int end = pageIndex * pageSize;
            if (end > sds.length) {
                end = sds.length;
            }
            for (int i = start; i < end; i++) {
                Document doc = searcher.doc(sds[i].doc);
                TokenStream titleTokenStream = new StandardAnalyzer().tokenStream("title",
                        new StringReader(doc.get("title")));
                TokenStream contentTokenStream = new StandardAnalyzer().tokenStream("content",
                        new StringReader(doc.get("content")));
                //TokenStream labelTokenStream = new StandardAnalyzer().tokenStream("label",new StringReader(doc.get("label")));

                String title = highlighter.getBestFragment(titleTokenStream, doc.get("title"));
                if (StringUtils.isBlank(title)) {
                    title = doc.get("title");
                }
                String content = highlighter.getBestFragment(contentTokenStream, doc.get("content"));
                if (StringUtils.isBlank(content)) {
                    content = doc.get("content");
                    if(content.length()>200){
                        content=content.substring(0,200);
                    }
                }
                // String label = highlighter.getBestFragment(labelTokenStream, doc.get("label"));

                wxArticle = DocumentConverter.convertFromDocument(doc, WxArticle.class);
                wxArticle.setTitle(title);
                wxArticle.setContent(content);
               /*  wxArticle.setLabel(doc.get("label"));
                wxArticle.setCode(doc.get("code"));
                wxArticle.setId(doc.get("id"));
                wxArticle.setCoverImg(doc.get("coverImg"));*/
                list.add(wxArticle);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    public void removeIndex(String id){
        indexService.removeIndex("article",id);
    }

}
