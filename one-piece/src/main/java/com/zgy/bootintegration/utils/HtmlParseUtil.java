package com.zgy.bootintegration.utils;

import com.zgy.bootintegration.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/5/23 0:59
 * @modified:
 */
@Component
public class HtmlParseUtil {
    // test, 获取内容
//    public static void main(String[] args) throws IOException {
//        // 获取请求
//        String url = "https://search.jd.com/Search?keyword=java";
//
//        // 解析网页
//        Document document = Jsoup.parse(new URL(url), 30000);
//        // 所有在js中可以使用的方法，都可以在此处使用
//        Element jGoodsList = document.getElementById("J_goodsList");
//        System.out.println(jGoodsList.html());
//        // 获取所有的li元素
//        Elements elements = jGoodsList.getElementsByTag("li");
//
//        // 获取元素中的内容，这里el 就是每一个li标签了
//        for (Element el : elements) {
//            // 很多网站的图片都是延迟加载的,source-data-lazy-img
//            String img = el.getElementsByTag("img").eq(0).attr("src");
//            String price = el.getElementsByClass("p-price").eq(0).text();
//            String title = el.getElementsByClass("p-name").eq(0).text();
//            System.out.println("=====================");
//            System.out.println(img);
//            System.out.println(price);
//            System.out.println(title);
//        }
//    }

    public List<Content> parseJd(String keyword) throws IOException {
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        Document document = Jsoup.parse(new URL(url), 30000);
        Element jGoodsList = document.getElementById("J_goodsList");
        // 展示抓取的数据
        // System.out.println(jGoodsList.html());
        Elements elements = jGoodsList.getElementsByTag("li");


        List<Content> goodsList = new ArrayList<>();

        for (Element el : elements) {
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            Content content = Content.builder().img(img).price(price).title(title).build();
            goodsList.add(content);
        }

        System.out.println(goodsList);
        return goodsList;
    }
}
