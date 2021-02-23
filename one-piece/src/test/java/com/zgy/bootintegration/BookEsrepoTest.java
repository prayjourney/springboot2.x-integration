package com.zgy.bootintegration;

import com.zgy.bootintegration.pojo.Book;
import com.zgy.bootintegration.service.ElasticSearchService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: z.g.y
 * @date: 2020/5/20
 * @description:
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
        System.out.println(esService.existsDocument("books", "1"));

    }

    @Test
    public void testGetBookDocument() throws IOException {
        System.out.println(esService.getDocument("books", "4"));

    }

    @Test
    public void testDeleteBookDocument() throws IOException {
        System.out.println(esService.deleteDocument("books", "2"));

    }

    // 会把其他的部分冲掉，覆盖其他部分，是一个全量的更新，而非增量更新
    @Test
    public void testUpdateBookDocument() throws IOException {
        // Book book = Book.builder().id(100).author("河东叟").name("封神演义").overview("封神演义讲述了武王伐纣的故事").build();
        Book book = Book.builder().issueDate(LocalDate.of(1522, 12, 9)).build();
        System.out.println(esService.updateBookDocument(book, "books", "3", 1));

    }

    @Test
    public void testUpdateBookDocumentByMap() throws IOException {
        Map<String, Object> mp = new HashMap<>();
        mp.put("name", "封神榜");
        mp.put("author", "河东智叟许仲林");
        mp.put("overview", "武王伐纣啊");
        mp.put("type", "==1213");
        System.out.println(esService.updateBookDocumentByMap(mp, "books", "3", 1));
    }

    @Test
    public void testBatchAddBookDocument() throws IOException {
        ArrayList as = new ArrayList();
        as.add(Book.builder().name("红楼梦").author("曹雪芹").overview("贾宝玉和林黛玉的爱情故事").type("古典爱情").build());
        as.add(Book.builder().name("三国演义").author("罗贯中").overview("东汉末年天下三分").type("历史战争").build());
        as.add(Book.builder().name("水浒传").author("施耐庵").overview("北宋末年宋江起义").type("战争").build());
        as.add(Book.builder().name("呐喊").author("鲁迅").overview("对封建礼教的控诉").type("白话").build());
        as.add(Book.builder().name("家春秋").author("巴金").overview("对封建礼教的控诉").type("白话 家庭").build());
        as.add(Book.builder().name("百年孤独").author("马尔克斯").overview("魔幻现实主义").type("拉美 魔幻现实").build());
        as.add(Book.builder().name("动物庄园").author("奥威尔").overview("乌托邦讽刺小说").type("反乌托邦").build());

        System.out.println(esService.batchAddDocument(as, "books", 200));

    }

    @Test
    public void testBatchAddBookDocument01() throws IOException {
        ArrayList as = new ArrayList();
        as.add(Book.builder().name("红楼梦").id(100).author("曹雪芹").overview("贾宝玉和林黛玉的爱情故事").build());
        as.add(Book.builder().name("三国演义").id(101).author("罗贯中").overview("东汉末年天下三分").type("历史战争").build());
        as.add(Book.builder().name("水浒传").id(102).author("施耐庵").type("战争").price(100).build());
        as.add(Book.builder().name("呐喊").id(103).author("鲁迅").overview("对封建礼教的控诉").type("白话").build());
        as.add(Book.builder().name("家春秋").id(800).author("巴金").type("白话 家庭").build());
        as.add(Book.builder().name("百年孤独").id(1500).author("马尔克斯").overview("魔幻现实主义").price(66).extras("拉美文学巨著")
                .type("拉美 魔幻现实").press("百花文艺出版社").issueDate(LocalDate.of(2018, 3, 6)).build());
        // as.add(Book.builder().name("动物庄园").id(1700).overview("***").type("反乌托邦").extras("一度被列为禁书").build());
        as.add(Book.builder().name("动物庄园").build());

        System.out.println(esService.batchAddDocument(as, "books", 200));

    }


    @Test
    public void testBatchAddBookDocument4Test() throws IOException {
        ArrayList as = new ArrayList();
        as.add(Book.builder().name("123").author("123").overview("123").type("123").build());
        as.add(Book.builder().name("234").author("234").overview("234").type("234").build());
        as.add(Book.builder().name("345").author("345").overview("345").type("345").build());
        as.add(Book.builder().name("456").author("456").overview("456").type("456").build());
        as.add(Book.builder().name("567").author("567").overview("567").type("567").build());
        as.add(Book.builder().name("678").author("678").overview("678").type("678").build());
        as.add(Book.builder().name("789").author("789").overview("789").type("789").build());

        System.out.println(esService.batchAddDocument(as, "books", 1000));

    }

    @Test
    public void testBatchDeleteBookDocument() throws IOException {
        List<String> ids = Arrays.asList("1", "2");
        System.out.println(esService.batchDeleteDocument("books", ids));

    }

    // todo: 批量更新

    /**
     * 批量更新，这个不行，有点问题，更新不到我们想要的字段上面去
     *
     * @throws IOException
     */
    @Test
    public void testBatchUpdateBookDocument() throws IOException {
        List<String> ids = Arrays.asList("1000", "1001", "1002");
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> mp1 = new HashMap<>();
        mp1.put("name", "张三12qqq3");
        mp1.put("author", "张三12qqqq3");
        mp1.put("issueDate", LocalDate.of(19921, 1, 1));
        Map<String, Object> mp2 = new HashMap<>();
        mp2.put("name", "王的怒吼");
        mp2.put("author", "张三");
        mp2.put("extras", "暂时还没有完成！");
        Map<String, Object> mp3 = new HashMap<>();
        mp3.put("name", "李四的歌");
        mp3.put("overview", "hello, 李四的歌是李四想唱给世界听得歌曲");
        mp3.put("issueDate", LocalDate.of(2019, 1, 1));
        mp3.put("price", 32.5f);
        list.add(mp1);
        list.add(mp2);
        list.add(mp3);
        System.out.println(esService.batchUpdateDocument(list, "books", ids));

    }

    @Test
    public void searchBookDocument() throws IOException {
        esService.searchAllDocument("books");
    }

    @Test
    public void searchBookDocumentByName() throws IOException {
        esService.searchDocumentByName("books", "三国演义");
    }
}
