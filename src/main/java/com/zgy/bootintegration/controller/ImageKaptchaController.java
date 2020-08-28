package com.zgy.bootintegration.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.zgy.bootintegration.utils.KaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * @Author renjiaxin
 * @Date 2020/8/28
 * @Description
 */
@Controller
public class ImageKaptchaController {
    @Autowired
    private Producer captchaProducer = null;

    @RequestMapping("/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @RequestMapping("/verifycode")
    @ResponseBody
    public String hello(HttpServletRequest request) {
        if (!KaptchaUtil.checkVerifyCode(request)) {
            return "验证码有误！";
        } else {
            return "hello,world!";
        }
    }
}
