package com.hxing.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.InitializingBean;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * Created by wanghongxing on 15/10/19.
 */
public class IndexContext implements InitializingBean {

    protected static IndexSearcher INDEX_SEARCHER = null;
    protected static IndexWriter INDEX_WRITER = null;
    protected static Analyzer INDEX_ANALYZER = null;
    protected static Directory INDEX_DIRECTORY = null;

    private IndexConfig indexConfig =null;

    public IndexConfig getIndexConfig() {
        return indexConfig;
    }

    public void setIndexConfig(IndexConfig indexConfig) {
        this.indexConfig = indexConfig;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        INDEX_ANALYZER = initAnalyzer(getIndexConfig().getLanguage());
        INDEX_DIRECTORY = initDirectory(getIndexConfig().getIndexFilePath());
        INDEX_SEARCHER = initIndexSearcher(INDEX_DIRECTORY);
        INDEX_WRITER = initIndexWriter(INDEX_DIRECTORY, INDEX_ANALYZER);
    }

    /**
     * 初始化IndexSearcher
     * @param directory
     * @return
     * @throws IOException
     */
    private static IndexSearcher initIndexSearcher(Directory directory) throws IOException {
        DirectoryReader reader = DirectoryReader.open(directory);
        return new IndexSearcher(reader);
    }

    /**
     * 初始化IndexWriter
     * @param directory
     * @param analyzer
     * @return
     * @throws IOException
     */
    private static IndexWriter initIndexWriter(Directory directory,Analyzer analyzer) throws IOException {
        return new IndexWriter(directory,new IndexWriterConfig(analyzer));
    }

    /**
     * 初始化索引路径对象
     * @param path index path
     * @return
     * @throws IOException
     */
    private static Directory initDirectory(String path) throws IOException {
        return FSDirectory.open(Paths.get(path));
    }

    /**
     * 初始化分词器
     * @return
     */
    private static Analyzer initAnalyzer(String language){
        return LanguageAnalyzer.nameOf(language).getAnalyzer();
    }

}
