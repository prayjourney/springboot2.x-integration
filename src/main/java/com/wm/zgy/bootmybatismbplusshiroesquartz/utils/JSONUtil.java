package com.wm.zgy.bootmybatismbplusshiroesquartz.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JSONUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String getJsonFromObject(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
