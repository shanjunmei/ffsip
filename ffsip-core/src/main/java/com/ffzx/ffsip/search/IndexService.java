package com.ffzx.ffsip.search;

import com.ffzx.ffsip.model.WxArticle;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */
@Component
public class IndexService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void buildIndex(Document doc,String type) {

        try {
            IndexWriter writer=getIndexWriter(type);
            writer.addDocument(doc);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.info("", e);
        }
    }

    public IndexWriter getIndexWriter(String type) {
        try {
            Directory directory = getIndexDirectory(type);

            IndexWriterConfig config = new IndexWriterConfig();
            return new IndexWriter(directory, config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Directory getIndexDirectory(String type) {
        String basePath=System.getProperty("index.stored.dir");
        if(StringUtils.isBlank(basePath)){
            basePath=System.getProperty("user.home");
        }
        try {
            String path = "ffsip/index/"+type;
            Path dir = Paths.get(basePath, path);
            if (!dir.toFile().exists()) {
                dir.toFile().mkdirs();
            }
            return FSDirectory.open(dir);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Document convert(Object article) {
        return DocumentConverter.convertToDocument(article);
    }

    public IndexSearcher getIndexSearcher(String type) throws IOException {
        Directory directory = getIndexDirectory(type);
        IndexReader reader = DirectoryReader.open(directory);
        return new IndexSearcher(reader);
    }

    public Highlighter getHighlighter(Query query) {
        Highlighter highlighter = null;
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'>",
                "</font>");
        highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
        highlighter.setTextFragmenter(new SimpleFragmenter(50));
        return highlighter;
    }
}
