package com.ffzx.ffsip.search;

import com.ffzx.ffsip.util.JsonConverter;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexableField;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/5.
 */
public class DocumentConverter {

    /**
     * @param doc
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T convertFromDocument(Document doc, Class<T> cls) {
        Iterator<IndexableField> it = doc.iterator();
        Map<String, String> params = new HashMap<>();
        while (it.hasNext()) {
            IndexableField field = it.next();
            String name = field.name();
            String value = field.stringValue();
            params.put(name, value);
        }
        return JsonConverter.convert(params, cls);

    }

    /**
     * @param entity
     * @return
     */
    public static Document convertToDocument(Object entity) {
        Map<String, Object> map = JsonConverter.convert(entity, Map.class);
        Document document = new Document();

        FieldType type = new FieldType();
        type.setStored(true);
        type.setIndexOptions(IndexOptions.DOCS);

        for (Map.Entry<String, Object> e : map.entrySet()) {
            String name = e.getKey();
            Object value = e.getValue();
            document.add(new Field(name, value.toString(), type));
        }
        return document;
    }
}
