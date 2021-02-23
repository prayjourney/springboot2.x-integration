package com.zgy.bootintegration.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeException;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * @author: z.g.y
 * @date: 2020/10/28
 * @description:
 */
@Service
@Slf4j
public class QRCodeService {
    // 自定义参数，这部分是Hutool工具封装的
    private static QrConfig initQrConfig() {
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.CYAN.getRGB());
        // 设置背景色（灰色）
        config.setBackColor(Color.GRAY.getRGB());
        return config;
    }

    /**
     * 生成到文件
     *
     * @param content
     * @param filepath
     */
    public void createQRCode2File(String content, String filepath) {
        try {
            QrCodeUtil.generate(content, initQrConfig(), FileUtil.file(filepath));
            log.info("生成二维码成功, 位置在：{}！", filepath);
        } catch (QrCodeException e) {
            log.error("发生错误！ {}！", e.getMessage());
        }
    }

    /**
     * 生成到流
     *
     * @param content
     * @param response
     */
    public void createQRCode2Stream(String content, HttpServletResponse response) {
        try {
            QrCodeUtil.generate(content, initQrConfig(), "png", response.getOutputStream());
            log.info("生成二维码成功!");
        } catch (QrCodeException | IOException e) {
            log.error("发生错误！ {}！", e.getMessage());
        }
    }
}
