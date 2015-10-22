package com.hxing.test;

import com.hxing.demo.model.Hotel;
import com.hxing.index.IndexClient;
import com.hxing.util.DocAdapter;
import com.hxing.util.DocBuilder;
import org.apache.lucene.document.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by wanghongxing on 15/10/20.
 */
public class TestIndex {

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:application_context.xml");

        Hotel hotel = new Hotel();
        hotel.setId(1l);
        hotel.setName("天津光合谷（天沐）温泉度假酒店");
        hotel.setTitle("天津光合谷（天沐）温泉度假酒店 [可加购温泉特惠票]尚品温泉慢生活 在花香虫鸣中酣然小憩");
        hotel.setDescrip("入住温泉度假酒店，洗涤一天的疲惫，享受一下酒店独有的园林SPA，在花香虫鸣中酣然小憩。\n" +
                "\n" +
                "亮点早知道：\n" +
                "1、酒店以“真山、真水、真温泉”为特色精心打造70多个室内外泡池，让您尽享健康活力之源。\n" +
                "2、酒店园区包括湿地公园、亲子动物园、CS真人训练基地、有机蔬菜大棚和生态餐厅等，还有免费摆渡车方便省时！\n" +
                "\n" +
                "适合度假的酒店\n" +
                "酒店位于湿地之内，建在一个岛上，环境非常好，尤其是夜晚，湖景非常美。周边也有很多游乐设施，适合度假。");

        try {

            IndexClient.save(new DocAdapter(hotel) {

                @Override
                public Document adapter() {

                    DocBuilder builder = DocBuilder.build();

                    builder.add(new LongField("id", hotel.getId(), Field.Store.YES))
                            .add(new TextField("name",hotel.getName(),Field.Store.YES))
                            .add(new TextField("title",hotel.getTitle(),Field.Store.YES))
                            .add(new StringField("descrip",hotel.getDescrip(),Field.Store.NO));

                    return builder.getDoc();

                }
            });

            IndexClient.commit();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("create index");
    }

}
