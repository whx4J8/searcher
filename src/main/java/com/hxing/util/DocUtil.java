package com.hxing.util;

import com.hxing.index.IndexContext;
import com.hxing.index.IndexHighPageResult;
import com.hxing.index.IndexPageResult;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Highlighter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghongxing on 15/10/21.
 */
public class DocUtil {

    public static List<Document> toDocuments(ScoreDoc[] scoreDocs,IndexSearcher searcher) throws IOException {

        List<Document> docs = new ArrayList<>();
        for(ScoreDoc scoreDoc : scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);
            docs.add(doc);
        }

        return docs;
    }

    public static List<Document> toDocuments(ScoreDoc[] scores) throws IOException {

        return toDocuments(scores, IndexContext.getIndexSearcher());

    }


    public static IndexHighPageResult toHighDocument(ScoreDoc[] scoreDocs,Highlighter highlighter) {

        for(ScoreDoc scoreDoc : scoreDocs){


        }

        return null;
    }


}
