package com.wm.zgy.bootmybatismbplusshiro.esdao;

import com.wm.zgy.bootmybatismbplusshiro.pojo.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author renjiaxin
 * @Date 2020/5/20
 * @Description
 */
@Repository
public interface BookDao extends ElasticsearchRepository<Book, Integer> {
    public List<Book> findBookByNameLike(String bookName);
    public Book save(Book book);
    public void deleteById(Integer id);
    public Integer countBookByAuthor(String name);
    public Book findBookByName(String bookName);


}
