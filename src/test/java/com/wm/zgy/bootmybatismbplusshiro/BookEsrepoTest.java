package com.wm.zgy.bootmybatismbplusshiro;

import com.wm.zgy.bootmybatismbplusshiro.pojo.Book;
import com.wm.zgy.bootmybatismbplusshiro.service.ElasticSearchService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @Author renjiaxin
 * @Date 2020/5/20
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookEsrepoTest {

    @Autowired
    ElasticSearchService esService;

    @Test
    public void testCreateIndex() throws IOException {
        Assert.assertEquals(true, esService.createIndex("ppp"));

    }

    @Test
    public void testGetIndex() throws IOException {
        Assert.assertEquals(true, esService.getIndex("hello"));

    }

    @Test
    public void testDeleteIndex() throws IOException {
        Assert.assertEquals(true, esService.deleteIndex("ppp"));

    }


    @Test
    public void testInsertBook() throws IOException {
        String overView = "中国古典四大名著之一，是最优秀的神话小说，也是一部群众创作和文人创作相结合的作品。" +
                "小说以整整七回的“大闹天宫”故事开始，把孙悟空的形象提到全书首要的地位。第八至十二回写如来说法，" +
                "观音访僧，魏徵斩龙，唐僧出世等故事，交待取经的缘起。从十四回到全书结束，写孙悟空被迫皈依佛教，保护唐僧取经，" +
                "在八戒、沙僧协助下，一路斩妖除魔，到西天成了“正果”";
        LocalDate date = LocalDate.of(1552, 2, 5);
        String extras = " xiyouji -test";
        Book book = Book.builder().id(1).name("西游记").author("吴承恩").price(27).press("长江文艺出版社").
                overview(overView).issueDate(date).type("古典神魔").extras(extras).build();
        System.out.println(book.toString());
        esService.addBookDocument(book, "books", "4", 1);

    }

    @Test
    public void testExistsBookDocument() throws IOException {
        System.out.println(esService.existsBookDocument("books", "1"));

    }

    @Test
    public void testGetBookDocument() throws IOException {
        System.out.println(esService.getBookDocument("books", "4"));

    }

    @Test
    public void testDeleteBookDocument() throws IOException {
        System.out.println(esService.getBookDocument("books", "2"));

    }
}
