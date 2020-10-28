package com.zgy.bootintegration;

import com.zgy.bootintegration.service.QRCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/10/28
 * @Modified by: java8之中的optional类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QRCodeServiceTest {
    @Autowired
    private QRCodeService qrCodeService;

    @Test
    public void createQRCode2FileTest() {
        qrCodeService.createQRCode2File("hello, world!", "d:/hello.png");
    }


}
