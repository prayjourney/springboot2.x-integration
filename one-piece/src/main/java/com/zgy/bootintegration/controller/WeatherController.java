package com.zgy.bootintegration.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/8/3 0:52
 * @modified:
 */
@RestController
@RequestMapping("city")
@Api(value = "城市天气的Controller", tags = "城市天气的接口")
public class WeatherController {
    Map<String, String> weathers = new HashMap<>();

    {
        weathers.put("北京", "空气质量差");
        weathers.put("天津", "空气质量中等");
        weathers.put("大同", "空气质量良好");
        weathers.put("哈尔滨", "空气质量一般");
        weathers.put("上海", "空气质量优");
        weathers.put("广州", "空气质量中等");
        weathers.put("成都", "空气质量良好");
        weathers.put("深圳", "空气质量一般");
        weathers.put("南京", "空气质量良好");
        weathers.put("杭州", "空气质量差");
    }

    // 跨域的解决
    @GetMapping("find")
    @CrossOrigin
    @ApiOperation(value = "按照name城市天气", notes = "按照name城市天气", httpMethod = "GET")
    public Map<String, String> findWeatherByCity(String name) {
        Map<String, String> map = new HashMap<>();
        String weather = getWeathers(name);
        map.put("message", weather);
        return map;
    }

    private String getWeathers(String name) {
        return weathers.get(name);
    }
}
