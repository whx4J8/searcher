package com.hxing.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * Created by wanghongxing on 15/10/21.
 */
public class Demo {

    public static void main(String[] args) throws IOException, ParseException {

        //Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_48);
        Analyzer analyzer = new IKAnalyzer();

        Directory directory = FSDirectory.open(new File("/Users/wanghongxing/_tmp/poi_chinese"));
        //writeIndex(analyzer,directory);
        searchIndex(analyzer, directory, "天津");

        directory.close();

    }

    /**
     * search text
     *
     * @param analyzer
     * @param directory
     * @param searchText
     * @throws IOException
     * @throws ParseException
     */
    public static void searchIndex(Analyzer analyzer,Directory directory,String searchText) throws IOException, ParseException {

        DirectoryReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        QueryParser parser = new QueryParser(Version.LUCENE_48,"name",analyzer);
        Query query = parser.parse(searchText);
        ScoreDoc[] hits = searcher.search(query,null,1000).scoreDocs;

        for(int i=0;i<hits.length;i++){
            Document hitDoc = searcher.doc(hits[i].doc);
            System.out.println(hitDoc.get("name"));
        }

        reader.close();

    }

    /**
     * store index on disk
     *
     * @param analyzer
     * @param directory
     * @throws IOException
     */
    public static void writeIndex(Analyzer analyzer, Directory directory) throws IOException {

        //索引写入配置
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48,analyzer);
        IndexWriter indexWriter = new IndexWriter(directory,config);

        Document doc = new Document();
        doc.add(new Field("fieldname", "我们是中国人", TextField.TYPE_STORED));

        indexWriter.addDocument(doc);
        indexWriter.commit();

    }


}
