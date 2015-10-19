package com.hxing.util;

import com.hxing.index.IndexContext;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghongxing on 15/10/19.
 */
public class DocumentUtil {

    public static List<Document> toDocuments(ScoreDoc[] scoreDocs,IndexSearcher searcher) throws IOException {

        List<Document> docs = new ArrayList<>();
        for(ScoreDoc scoreDoc : scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);
            docs.add(doc);
        }

        return docs;
    }

}
