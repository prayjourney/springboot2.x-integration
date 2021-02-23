package com.zgy.bootintegration;

import com.alibaba.excel.EasyExcel;
import com.zgy.bootintegration.utils.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: z.g.y
 * @date: 2020/6/23
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyExcelTest {

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

    // 简单的读
    @Test
    public void simpleReadTest() {
        String fileName = "d:\\数据标注6-03.xlsx";
        //ExcelReaderBuilder readerBuilder = EasyExcel.read(fileName, new NoModelDataListener());
        //readerBuilder.sheet(0).doRead();
    }

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


}
