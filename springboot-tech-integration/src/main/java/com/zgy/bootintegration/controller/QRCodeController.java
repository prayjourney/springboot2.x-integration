package com.zgy.bootintegration.controller;

import com.zgy.bootintegration.utils.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author renjiaxin
 * @Date 2020/10/28
 * @Description
 */
@RestController
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
