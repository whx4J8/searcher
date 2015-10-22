package com.hxing.test;

import com.hxing.index.IndexClient;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * Created by wanghongxing on 15/10/21.
 */
public class TestSearch {

    public static void main(String[] args){

        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:application_context.xml");

        try {
            List<Document> docs = IndexClient.sampleQuery("title","度假");
            for(Document doc:docs){
                System.out.println(doc.getField("id"));
                System.out.println(doc.getField("name"));
                System.out.println(doc.getField("title"));
                System.out.println(doc.getField("descrip"));
            }


        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
