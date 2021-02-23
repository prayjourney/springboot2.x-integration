package com.zgy.bootintegration;

import com.zgy.bootintegration.service.QRCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/10/28
 * @modified:
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
