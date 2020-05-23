package com.wm.zgy.bootmybatismbplusshiroesquartz;

import com.wm.zgy.bootmybatismbplusshiroesquartz.utils.HtmlParseUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/23 1:34
 * @Modified by:
 */
public class HtmlParseUtilTest {
    @Test
    public void testParseJd() throws IOException {
        HtmlParseUtil htmlParseUtil = new HtmlParseUtil();
        System.out.println(htmlParseUtil.parseJd("vue"));
    }
}
