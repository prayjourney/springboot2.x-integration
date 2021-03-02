package com.zgy.learn.springsecurity.utils;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: pray-journey.io
 * @date: 2021/2/26
 * @description: Response信息装配util
 */
public class ResponseUtil {

    public static void responseJson(HttpServletResponse response, int status, Object data) {
        try {
            // 跨域设置
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            // 设置为json格式
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(status);
            // 装配返回值，状态码+token
            JSONObject object = new JSONObject();
            object.put("status", status);
            object.put("token", data);

            response.getWriter().write(object.toString());

            // PrintWriter直接打印到屏幕上, response.getWriter().write(object.toString());作为数据返回给后前端
            // PrintWriter这种一般是使用在同步的方法上面, 直接作为给前端返回的结果，其实一样，没有本质区别
            // * PrintWriter out = response.getWriter();
            // * out.write("登录失败!");
            // * out.flush();
            // * out.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}