package com.hxing.util;

import com.hxing.index.IndexContext;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

/**
 * Created by wanghongxing on 15/10/21.
 */
public class QueryBuilder {

    /**
     * 创建SampleQuery对象
     * @param field
     * @param word
     * @return
     * @throws ParseException
     */
    public static Query buildSampleQuery(String field,String word) throws ParseException {

        QueryParser parser = new QueryParser(Version.LUCENE_48,field,
                IndexContext.getIndexAnalyzer());

        return parser.parse(word);
    }

}
