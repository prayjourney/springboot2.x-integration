package com.zgy.learn.verification.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: z.g.y
 * @date: 2021/1/25
 */
@Controller
@RequestMapping("hutool")
@Slf4j
public class HutoolCaptchaController {
    @GetMapping("page")
    public String page() {
        return "verifypage";
    }

    @GetMapping(value = "captcha")
    public String createCaptchaCode(HttpServletResponse response, HttpSession session) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // 禁止浏览器缓存
        response.setHeader("Pragma", "no-cache");
        // 输出内容为jpeg类型的图片
        response.setContentType("image/jpeg");

        // 生成验证码图片
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(120, 30, 4, 6);

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            // 图形验证码写出，可以写出到流，也可以写出到文件如circleCaptcha.write("d:/circle25.jpeg");
            circleCaptcha.write(outputStream);
            // 从带有圆圈类型的图形验证码图片中获取它的字符串验证码(获取字符串验证码要在图形验证码写出wirte后面才行，不然得到的值为null)
            String code = circleCaptcha.getCode();
            // 将字符串验证码保存到session中
            session.setAttribute("code", code);
            log.info("生成的验证码：{}", code);
            log.info("sessionId:{}", session.getId());
            // 关闭流
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回生成的验证码
        return circleCaptcha.getCode();
    }

    @GetMapping("/verify")
    @ResponseBody
    public String verify(HttpServletRequest request, String code) {
        // 从session中获取验证码的String值
        String codeInfo = (String) request.getSession().getAttribute("code");
        if (codeInfo.equals(code)) {
            return "验证通过";
        } else {
            // 此处需要重新生成一个，否则有点问题
            return "验证码错误！";
        }
    }
}
