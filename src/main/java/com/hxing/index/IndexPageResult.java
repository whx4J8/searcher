package com.hxing.index;

import org.apache.lucene.document.Document;

import java.util.List;

/**
 * Created by wanghongxing on 15/10/21.
 */
public class IndexPageResult {

    private int totalHits;
    private List<Document> docs;

    public IndexPageResult(int totalHits, List<Document> docs) {
        this.totalHits = totalHits;
        this.docs = docs;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<Document> getDocs() {
        return docs;
    }

    public void setDocs(List<Document> docs) {
        this.docs = docs;
    }
}
