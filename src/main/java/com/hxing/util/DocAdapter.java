package com.hxing.util;

import org.apache.lucene.document.Document;

/**
 * Created by wanghongxing on 15/10/21.
 */
public abstract class DocAdapter {

    /**
     * 源对象
     */
    private Object object;

    public DocAdapter(Object object) {
        this.object = object;
    }

    /**
     * 适配Document对象
     * @return
     */
    public abstract Document adapter();
}
