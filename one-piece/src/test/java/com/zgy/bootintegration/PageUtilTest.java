package com.zgy.bootintegration;

import com.zgy.bootintegration.utils.PageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/5/28 0:50
 * @modified:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageUtilTest {

    @Test
    public void testList2Page() throws Exception {
        List<String> ls = new ArrayList<>();
        ls.add("a1a2a1");
        ls.add("a1a2a2");
        ls.add("a1a2a3");
        ls.add("a1a2a4");
        ls.add("a1a2a5");
        ls.add("a1a2a6");
        ls.add("a1a2a7");
        ls.add("a1a2a8");
        ls.add("a1a2a9");
        ls.add("a1a2a10");
        ls.add("a1a2a11");
        ls.add("a1a2a12");
        ls.add("a1a2a13");
        ls.add("a1a2a14");
        ls.add("a1a2a15");

        int pageSize = 4;
        int iteratorNum = PageUtil.getIteratorNum(ls, pageSize);
        for (int pageNo = 0; pageNo < iteratorNum; pageNo++) {
            // List<String> ss = PageUtil.list2Page(ls, pageSize, pageNo, iteratorNum);
            List<String> ss = PageUtil.list2Page(ls, pageSize, pageNo);
            for (String s : ss) {
                System.out.println(s);
            }
            System.out.println("============");
        }
    }


    @Test
    public void testMap2Page() throws Exception {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        map.put(5, "e");
        map.put(6, "f");
        map.put(7, "g");
        map.put(8, "h");
        map.put(9, "i");
        map.put(10, "j");
        map.put(11, "k");
        map.put(12, "l");
        map.put(13, "m");
        map.put(14, "n");
        map.put(15, "o");
        map.put(16, "p");
        Map<Integer, String> integerStringMap = PageUtil.map2Page(map, 15, 0);
        Set<Map.Entry<Integer, String>> entries = integerStringMap.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            System.out.println("key : " + next.getKey() + ", value : " + next.getValue());
        }

    }
}
