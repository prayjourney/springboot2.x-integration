package com.zgy.learn.qrcode.controller;

import com.zgy.learn.qrcode.util.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-01-25
 * @modified:
 */
@Controller
@Slf4j
public class QRCodeController {

    @GetMapping("qrCode")
    public void getQRCode(String codeContent, HttpServletResponse response) {
        System.out.println("codeContent=" + codeContent);
        try {
            /*
             * 调用工具类生成二维码并输出到输出流中
             */
            QRCodeUtil.createCodeToOutputStream(codeContent, response.getOutputStream());
            log.info("成功生成二维码!");
        } catch (IOException e) {
            log.error("发生错误， 错误信息是：{}！", e.getMessage());
        }
    }

}
