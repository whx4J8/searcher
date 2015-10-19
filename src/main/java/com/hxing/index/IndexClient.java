package com.hxing.index;

import com.hxing.util.DocumentUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghongxing on 15/10/19.
 */
public class IndexClient{

    public List<Document> sampleQuery(String field,String word) throws ParseException, IOException {
        return sampleQuery(field,word,20);
    }

    /**
     *
     * @param field
     * @param word
     * @param num
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public List<Document> sampleQuery(String field,String word,int num) throws ParseException, IOException {
        QueryParser parser = new QueryParser(field,IndexContext.INDEX_ANALYZER);
        Query query = parser.parse(word);
        ScoreDoc[] hits = IndexContext.INDEX_SEARCHER.search(query,num).scoreDocs;
        return DocumentUtil.toDocuments(hits,IndexContext.INDEX_SEARCHER);
    }

    public void delete(Term term) throws IOException {
        IndexContext.INDEX_WRITER.deleteDocuments(term);
    }

    public void save(Document document) throws IOException {
        IndexContext.INDEX_WRITER.addDocument(document);
    }

    public void save(DocumentAdapter adapter) throws IOException {
        save(adapter.adapter());
    }


    public void commitWriter() throws IOException {
        IndexContext.INDEX_WRITER.commit();
    }


}
