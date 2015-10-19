package com.hxing.index;

import com.hxing.exception.IndexConfigException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * Created by wanghongxing on 15/10/19.
 */
public enum LanguageAnalyzer {

    Chinese(0,"Chinese",new IKAnalyzer()),
    English(1,"English",new StandardAnalyzer())
    ;

    LanguageAnalyzer(int value, String name,Analyzer analyzer) {
        this.value = value;
        this.name = name;
        this.analyzer = analyzer;
    }

    private int value;
    private String name;
    private Analyzer analyzer;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public static LanguageAnalyzer nameOf(String name){
        for(LanguageAnalyzer languageAnalyzer : LanguageAnalyzer.values()){
            if(languageAnalyzer.name.equals(name)){
                return languageAnalyzer;
            }
        }

        throw new IndexConfigException("language config error,not have " + name + " analyzer");
    }
}
