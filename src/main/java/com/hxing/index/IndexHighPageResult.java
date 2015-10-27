package com.hxing.index;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.highlight.Highlighter;

import java.util.List;

/**
 *
 * Created by wanghongxing on 15/10/21.
 */
public class IndexHighPageResult extends IndexPageResult {

    private Highlighter highlighter;    //高亮器

    public IndexHighPageResult(int totalHits, List<Document> docs) {
        super(totalHits, docs);
    }

    public IndexHighPageResult(int totalHits, List<Document> docs, Highlighter highlighter) {
        super(totalHits, docs);
        this.highlighter = highlighter;
    }

    public Highlighter getHighlighter() {
        return highlighter;
    }

    public void setHighlighter(Highlighter highlighter) {
        this.highlighter = highlighter;
    }

}

