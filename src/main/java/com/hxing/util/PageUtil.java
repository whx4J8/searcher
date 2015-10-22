package com.hxing.util;

import com.hxing.index.IndexPageResult;

/**
 * Created by wanghongxing on 15/10/21.
 */
public class PageUtil {

    public static int getStart(int pageSize,int curPage){

        return (curPage - 1)*pageSize;
    }

    public static int getEnd(int pageSize,int curPage){

        return curPage * pageSize;
    }

}
