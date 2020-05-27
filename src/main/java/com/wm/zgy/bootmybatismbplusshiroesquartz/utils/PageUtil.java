package com.wm.zgy.bootmybatismbplusshiroesquartz.utils;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: renjiaxin
 * @Despcription: 数据的一个分页， 分页重点在于搞清楚分多少页，边界怎么处理，是不是完整分页，如同下面的介绍
 * @Date: Created in 2020/5/28 0:47
 * @Modified by:
 */
@Component
public class PageUtil {
    /**
     * 获取第pageNo页面， 从页面的起始值从0开始
     * 15个数据，页面是5-->3组, 完整3组, 15/5=3;
     * 15个数据，页面是7--->3组, 完整2组, 残缺1组, 15/7=2 ...1;
     * 所以，可以分页的数据，需要在外面决定好
     */
    public static <T> List<T> list2Page(List<T> ls, int pageSize, int pageNo, int iteratorNum) {
        // 是否可以完整分页, 也就是是否有残余的数据
        boolean complete = (ls.size() % pageSize) == 0 ? true : false;

        if (pageNo < 0 || pageNo > iteratorNum) {
            // 也可直接报错
            return null;
        } else if (pageNo >= 0 && pageNo < iteratorNum - 1) {
            return ls.subList(pageNo * pageSize, pageNo * pageSize + pageSize);

        } else {
            // (pageNo > 0 && pageNo == iteratorNum)
            return ls.subList(pageNo * pageSize, ls.size());
        }
    }

    /**
     * 获取分页的总数
     *
     * @param ls
     * @param pageSize
     * @return
     */
    public static <T> int getIteratorNum(List<T> ls, int pageSize) {
        int base = ls.size() / pageSize;
        boolean complete = (ls.size() % pageSize) == 0 ? true : false;
        if (complete == true) {
            return base;
        } else {
            return base + 1;
        }
    }

}
