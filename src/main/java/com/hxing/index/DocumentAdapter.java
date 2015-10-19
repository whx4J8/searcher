package com.hxing.index;

import org.apache.lucene.document.Document;

/**
 * Created by wanghongxing on 15/10/19.
 */
public abstract class DocumentAdapter {

    /**
     * 源对象
     */
    private Object object;

    public DocumentAdapter(Object object) {
        this.object = object;
    }

    /**
     * 适配Document对象
     * @return
     */
    public abstract Document adapter();

}
