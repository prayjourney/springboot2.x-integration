package com.zgy.bootintegration.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: z.g.y
 * @description: 数据的一个分页， 分页重点在于搞清楚分多少页，边界怎么处理，是不是完整分页，如同下面的介绍
 * @date: Created in 2020/5/28 0:00
 * @modified:
 */
@Component
public class PageUtil {
    /**
     * 获取分页的总数
     *
     * @param ls       需要分页的list
     * @param pageSize 页面大小
     * @return 按照此页面分页可以得到的分页数量
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


//    /**
//     * 获取分页之后，该页的数据
//     *
//     * 获取第pageNo页面， 从页面的起始值从0开始
//     * 15个数据，页面是5-->3组, 完整3组, 15/5=3;
//     * 15个数据，页面是7--->3组, 完整2组, 残缺1组, 15/7=2 ...1;
//     * 所以，可以分页的数据，需要在外面决定好
//     */
//    public static <T> List<T> list2Page(List<T> ls, int pageSize, int pageNo, int iteratorNum) {
//        // 是否可以完整分页, 也就是是否有残余的数据
//        boolean complete = (ls.size() % pageSize) == 0 ? true : false;
//
//        if (pageNo < 0 || pageNo > iteratorNum) {
//            // 也可直接报错
//            return null;
//        } else if (pageNo >= 0 && pageNo < iteratorNum - 1) {
//            return ls.subList(pageNo * pageSize, pageNo * pageSize + pageSize);
//
//        } else {
//            // (pageNo > 0 && pageNo == iteratorNum)
//            return ls.subList(pageNo * pageSize, ls.size());
//        }
//    }

    /**
     * 获取分页之后，该页的数据
     *
     * @param list     需要分页的List数据
     * @param pageSize 页面大小
     * @param pageNo   页面编码
     * @param <T>      T参数类型
     * @return pageNo页面的内容，相当于是一个子List
     * @throws Exception exception
     */
    public static <T> List<T> list2Page(List<T> list, int pageSize, int pageNo) throws Exception {
        // 获取当前分页条件下的页面数量
        int allPageNumber = getIteratorNum(list, pageSize);

        if (pageNo < 0 || pageNo > allPageNumber) {
            // 也可直接报错
            throw new Exception("page number is out of boundary!");
        } else if (pageNo >= 0 && pageNo < allPageNumber - 1) {
            return list.subList(pageNo * pageSize, pageNo * pageSize + pageSize);
        } else {
            // (pageNo > 0 && pageNo == iteratorNum)
            return list.subList(pageNo * pageSize, list.size());
        }
    }

    /**
     * 获取分页的总数
     *
     * @param map      需要分页的map
     * @param pageSize 页面大小
     * @return 按照此页面分页可以得到的分页数量
     */
    public static <T, V> int getIteratorNum(Map<T, V> map, int pageSize) {
        int base = map.size() / pageSize;
        boolean complete = (map.size() % pageSize) == 0 ? true : false;
        if (complete == true) {
            return base;
        } else {
            return base + 1;
        }
    }

    /**
     * map的分页
     *
     * @param map      需要分页的数据
     * @param pageSize 页面大小
     * @param pageNo   要获取的页面编码
     * @param <T>      T参数类型
     * @param <V>      V参数类型
     * @return pageNo页面的内容，相当于是一个子Map
     * @throws Exception exception
     */
    public static <T, V> Map<T, V> map2Page(Map<T, V> map, int pageSize, int pageNo) throws Exception {
        // 获取当前分页条件下的页面数量
        int allPageNumber = getIteratorNum(map, pageSize);

        // 需要先把Map转换成List, 然后去遍历，这样可以让操作有顺序的进行
        Set<T> ts = map.keySet();
        List<T> keyList = new ArrayList<>();
        List<V> valueList = new ArrayList<>();
        for (T t : ts) {
            keyList.add(t);
            valueList.add(map.get(t));
        }

        if (pageNo < 0 || pageNo > allPageNumber) {
            throw new Exception("page number is out of boundary!");
        } else if (pageNo >= 0 && pageNo < allPageNumber - 1) {
            List<T> tempKeyList = keyList.subList(pageNo * pageSize, pageNo * pageSize + pageSize);
            List<V> tempValueList = valueList.subList(pageNo * pageSize, pageNo * pageSize + pageSize);
            Map<T, V> tvHashMap = new HashMap<>();
            for (int i = 0; i < tempKeyList.size(); i++) {
                // 1.此处可以Map的方式get, 2.可以通过list按顺序获取
                tvHashMap.put(tempKeyList.get(i), tempValueList.get(i));
            }
            return tvHashMap;
        } else {
            List<T> tempKeyList = keyList.subList(pageNo * pageSize, map.size());
            List<V> tempValueList = valueList.subList(pageNo * pageSize, map.size());
            Map<T, V> tvHashMap = new HashMap<>();
            for (int i = 0; i < tempKeyList.size(); i++) {
                // 1.此处可以Map的方式get, 2.可以通过list按顺序获取
                tvHashMap.put(tempKeyList.get(i), tempValueList.get(i));
            }
            return tvHashMap;
        }
    }


}
