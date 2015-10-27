package com.hxing.index;

import com.hxing.util.DocAdapter;
import com.hxing.util.DocUtil;
import com.hxing.util.PageUtil;
import com.hxing.util.QueryBuilder;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wanghongxing on 15/10/19.
 */

public class IndexClient{

    /**
     *
     * @param field
     * @param word
     * @param pageSize
     * @param curPage
     * @param max
     * @return
     * @throws ParseException
     * @throws IOException
     */
    private static IndexHighPageResult pageHiglightQuery(String field,String word,int pageSize,int curPage,int max) throws ParseException, IOException {

        Query query = QueryBuilder.buildSampleQuery(field, word);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span>", "</span>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter,new QueryScorer(query));

        ScoreDoc[] totalScoreDocs = IndexContext.INDEX_SEARCHER.search(query,max).scoreDocs;

        int start = PageUtil.getStart(pageSize, curPage);
        int end = PageUtil.getEnd(pageSize, curPage);
        List<ScoreDoc> pageScoreDocs = new ArrayList<>();
        for(int i=start ;i<end;i++){
            if(i>=totalScoreDocs.length) break;
            pageScoreDocs.add(totalScoreDocs[i]);
        }

        return new IndexHighPageResult(totalScoreDocs.length,DocUtil.toDocuments(pageScoreDocs),highlighter);
    }

    public static IndexHighPageResult pageHiglightQuery(String field,String word,int pageSize,int curPage) throws IOException, ParseException {
        return pageHiglightQuery(field,word,pageSize, curPage, pageSize*(curPage+1));
    }

    /**
     *
     * @param field     搜索域
     * @param word      被搜索短语
     * @param pageSize  没页数量
     * @param curPage   当前页码
     * @param max       最大查询数量
     * @return
     */
    private static IndexPageResult pageQuery(String field,String word,int pageSize,int curPage,int max) throws IOException, ParseException {

        Query query = QueryBuilder.buildSampleQuery(field, word);
        ScoreDoc[] totalScoreDocs = IndexContext.INDEX_SEARCHER.search(query,max).scoreDocs;

        int start = PageUtil.getStart(pageSize, curPage);
        int end = PageUtil.getEnd(pageSize, curPage);
        List<ScoreDoc> pageScoreDocs = new ArrayList<>();
        for(int i=start ;i<end;i++){
            if(i>=totalScoreDocs.length) break;
            pageScoreDocs.add(totalScoreDocs[i]);
        }

        return new IndexPageResult(totalScoreDocs.length, DocUtil.toDocuments(pageScoreDocs));
    }


//    private static List<ScoreDoc> pageQuery(Query query,int pageSize,int curPage,int max) throws IOException {
//        ScoreDoc[] totalScoreDocs = IndexContext.INDEX_SEARCHER.search(query,max).scoreDocs;
//
//        int start = PageUtil.getStart(pageSize, curPage);
//        int end = PageUtil.getEnd(pageSize, curPage);
//        List<ScoreDoc> pageScoreDocs = new ArrayList<>();
//        for(int i=start ;i<end;i++){
//            if(i>=totalScoreDocs.length) break;
//            pageScoreDocs.add(totalScoreDocs[i]);
//        }
//
//        return pageScoreDocs;
//    }

    /**
     *
     * @param field     搜索域
     * @param word      被搜索短语
     * @param pageSize  没页数量
     * @param curPage   当前页码
     * @return
     */
    public static IndexPageResult pageQuery(String field,String word,int pageSize,int curPage) throws ParseException, IOException {
        return pageQuery(field, word, pageSize, curPage, pageSize*(curPage+1));
    }


    /**
     * 缺省型单一域查询
     *
     * @param field
     * @param word
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static List<Document> sampleQuery(String field,String word) throws ParseException, IOException {

        return sampleQuery(field, word, 20);
    }

    /**
     * 单一域查询
     *
     * @param field
     * @param word
     * @param num
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static List<Document> sampleQuery(String field,String word,int num) throws ParseException, IOException {
        ScoreDoc[] hits = IndexContext.INDEX_SEARCHER.search(QueryBuilder.
                buildSampleQuery(field, word),null,num).scoreDocs;

        return DocUtil.toDocuments(Arrays.asList(hits), IndexContext.INDEX_SEARCHER);
    }

    public static void delete(Term term) throws IOException {
        IndexContext.INDEX_WRITER.deleteDocuments(term);
    }

    public static void save(Document document) throws IOException {
        IndexContext.INDEX_WRITER.addDocument(document);
    }


    public static void save(List<Document> documents) throws IOException {
        IndexContext.INDEX_WRITER.addDocuments(documents);
    }

    public static void save(DocAdapter adapter) throws IOException {
        save(adapter.adapter());
    }

    public static void commit() throws IOException {
        IndexContext.INDEX_WRITER.commit();
    }

}
