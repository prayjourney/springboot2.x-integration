package com.zgy.bootintegration;

import com.zgy.bootintegration.pojo.CatSerialize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

/**
 * @author: z.g.y
 * @date: 2020/6/23
 * @description: https://www.iteye.com/blog/xiebh-121311
 * https://blog.csdn.net/u013870094/article/details/82765907
 * https://blog.csdn.net/qq_35890572/article/details/81630052
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CatSerializeTest {
    @Test
    public void testSerial() throws IOException {
        CatSerialize catSerialize =
                CatSerialize.builder().id(1).kind("波斯猫").name("二黑").birthday(LocalDate.now()).build();
        // ObjectOutputStream对象输出流，将catSerialize对象存储到catSerialize.txt文件中，完成对catSerialize对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("d://catSerialize.txt")));
        oos.writeObject(catSerialize);
        System.out.println("序列化成功!");
        oos.close();
    }


    @Test
    public void testDeSerial() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("d://catSerialize.txt")));
        // ObjectInputStream对象输入流，从catSerialize.txt文件中读取对象，完成对象的反序列化操作
        CatSerialize myCat = (CatSerialize) ois.readObject();
        System.out.println("CatSerialize对象反序列化成功!");
        ois.close();
        System.out.println(myCat.toString());
    }
}
