package com.zgy.bootintegration.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * @author: z.g.y
 * @date: 2020/5/20
 * @description:
 */
@Setter
@Getter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
//@Document(indexName = "book", type = "mybook")
public class Book {
    // 书籍id
    private int id;
    // 书籍名字
    private String name;
    // 作者
    private String author;
    // 书籍简介
    private String overview;
    // 书籍类别
    private String type;
    // 出版社
    private String press;
    // 书籍价格
    private double price;
    // 出版日期
    private LocalDate issueDate;
    // 其他
    private String extras;
}
