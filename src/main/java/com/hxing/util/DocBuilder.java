package com.hxing.util;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexableField;

/**
 * Created by wanghongxing on 15/10/20.
 */
public class DocBuilder {

    private Document document;

    private DocBuilder(Document document){
        this.document = document;
    }

    public static DocBuilder build(Document document){
        return new DocBuilder(document);
    }

    public static DocBuilder build(){
        return new DocBuilder(new Document());
    }

    public DocBuilder add(IndexableField field){
        document.add(field);
        return this;
    }

    public Document getDoc(){
        return document;
    }

}
