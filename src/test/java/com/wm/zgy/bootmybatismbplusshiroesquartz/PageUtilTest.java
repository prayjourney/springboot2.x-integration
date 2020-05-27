package com.wm.zgy.bootmybatismbplusshiroesquartz;

import com.wm.zgy.bootmybatismbplusshiroesquartz.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/28 0:50
 * @Modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageUtilTest {

    @Test
    public void testList2Page() {
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
            List<String> ss = PageUtil.list2Page(ls, pageSize, pageNo, iteratorNum);
            for (String s : ss) {
                System.out.println(s);
            }
            System.out.println("============");
        }
    }
}
