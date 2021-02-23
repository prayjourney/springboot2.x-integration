package com.zgy.bootintegration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: z.g.y
 * @date: 2020/6/28
 * @description: jdbc:
 * https://blog.csdn.net/lhb0101/article/details/84914376
 * https://blog.csdn.net/sj5wen/article/details/77387867
 * https://www.iteye.com/blog/humlzy-2382685
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate template;

    @Test
    public void testJdbcTemplate() {
        String sql = "select * from student";
        List<Map<String, Object>> maps = template.queryForList(sql);
        Iterator<Map<String, Object>> iterator = maps.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> nextMap = iterator.next();
            Set<Map.Entry<String, Object>> entries = nextMap.entrySet();
            Iterator<Map.Entry<String, Object>> entryIterator = entries.iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<String, Object> objectEntry = entryIterator.next();
                System.out.println(objectEntry.getKey() + ", " + objectEntry.getValue());
            }
        }

        System.out.println(maps.size());
    }


    @Test
    public void testInsertJdbc() {
        String sql = "insert into student(`stId`,`stName`,`stAge`,`stGender`,`classId`,`hobbyGroupId`,`stHome`,`cityId`)" +
                " values(?, ?, ?, ?, ?, ?, ?, ?)";
        int i = template.update(sql, 99, "张飞", 22, 0, 1, 2, "北京", 22);
    }


    @Test
    public void testInsertJdbc02() {
        String sql = "insert into student(`stName`,`stAge`,`stGender`,`classId`,`hobbyGroupId`,`stHome`,`cityId`)" +
                " values(?, ?, ?, ?, ?, ?, ?)";
        int i = template.update(sql, "张飞", 22, 0, 1, 2, "北京", 99);
    }
}
