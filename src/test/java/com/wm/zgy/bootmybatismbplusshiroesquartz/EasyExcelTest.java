package com.wm.zgy.bootmybatismbplusshiroesquartz;

import com.alibaba.excel.EasyExcel;
import com.wm.zgy.bootmybatismbplusshiroesquartz.utils.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author renjiaxin
 * @Date 2020/6/23
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyExcelTest {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class Apple {
        private Integer id;
        private String name;
        private Integer price;
        private String origin;
        private Date date;
    }

    public List<Apple> cats() {
        Stream<Apple> catStream = Stream.of(
                Apple.builder().id(12).origin("郑州").name("小野猫").price(100).date(new Date()).build(),
                Apple.builder().id(3).origin("郑州").name("张三").price(130).date(new Date()).build(),
                Apple.builder().id(64).origin("郑州").name("lili").price(440).date(new Date()).build(),
                Apple.builder().id(5).origin("郑州").name("张2三").price(55).date(new Date()).build(),
                Apple.builder().id(22).origin("成都").name("pipiya").price(200).date(new Date()).build(),
                Apple.builder().id(133).origin("成都").name("MG").price(30).date(new Date()).build(),
                Apple.builder().id(12).origin("广州").name("kali").price(790).date(new Date()).build(),
                Apple.builder().id(61).origin("广州").name("999").price(90).date(new Date()).build()
        );
        List<Apple> apples = catStream.filter(x -> x.getId() > 5).sorted(
                (x, y) -> {
                    if (x.getPrice() >= y.getPrice()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
        ).collect(Collectors.toList());
        return apples;
    }

    // 简单的写
    @Test
    public void simpleWriteTest() throws IOException {
        String fileName = "d://write" + System.currentTimeMillis() + ".xlsx";
        boolean exists = FileUtil.fileExists(fileName);
        if (!exists) {
            FileUtil.createFile(fileName);
        }
        EasyExcel.write(fileName, Apple.class).sheet("myapple").doWrite(cats());
    }

    @Test
    public void testCreateDir(){
        // 创建了一个3层的目录, d -> c -> 4.txt
        String path ="d://b/c/4.txt";
        File file = new File(path);
        file.mkdirs();
    }

    @Test
    public void testIsMultiPath(){
        System.out.println(FileUtil.isMultiPath("d:\\1\\2\\3\\4.txt"));
    }

    // 测试系统的分隔符
    @Test
    public void testFileSperator(){
        System.out.println(File.separator);
    }

    @Test
    public void testSystem(){
        String osName = System.getProperty("os.name");
        String userName = System.getProperty("user.name");
        System.out.println(osName);
        System.out.println(userName);
    }

}
