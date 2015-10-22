package com.hxing.index;

import com.sun.tools.javac.jvm.ClassFile;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * Created by wanghongxing on 15/10/19.
 */
public class IndexContext implements InitializingBean {

    /**
     * 同包下可以任意修改获取
     */
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
        INDEX_WRITER = initIndexWriter(INDEX_DIRECTORY, INDEX_ANALYZER);
        INDEX_WRITER.commit();                              //如果没有索引文件,先commit
        INDEX_SEARCHER = initIndexSearcher(INDEX_DIRECTORY);

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
        return new IndexWriter(directory,new IndexWriterConfig(Version.LUCENE_48,analyzer));
    }

    /**
     * 初始化索引路径对象
     * @param path index path
     * @return
     * @throws IOException
     */
    private static Directory initDirectory(String path) throws IOException {
        return FSDirectory.open(new File(path));
    }

    /**
     * 初始化分词器
     * @return
     */
    private static Analyzer initAnalyzer(String language){
        return LanguageAnalyzer.nameOf(language).getAnalyzer();
    }

    public static IndexSearcher getIndexSearcher() {
        return INDEX_SEARCHER;
    }

    public static IndexWriter getIndexWriter() {
        return INDEX_WRITER;
    }

    public static Analyzer getIndexAnalyzer() {
        return INDEX_ANALYZER;
    }

    public static Directory getIndexDirectory() {
        return INDEX_DIRECTORY;
    }
}
