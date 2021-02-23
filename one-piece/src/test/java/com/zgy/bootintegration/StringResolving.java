package com.zgy.bootintegration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: z.g.y
 * @date: 2020/7/3
 * @description:
 */
public class StringResolving {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        String str = "query=12313123&amp;word=rewqwtrwfdtgRETWEdsgdf+阿股份大厦&amp;type=JOB&amp;word=123123213123213213&amp;type=JOB";
        String[] split = str.split(";");
        if (split.length % 2 == 0) {
            System.out.println(-1);
        } else {
            List<String> collect = Arrays.stream(split).map(x -> {
                if (x.contains("&amp")) {
                    int i = x.indexOf("&amp");
                    return x.substring(0, i);
                }
                return x;
            }).map(y -> {
                if (y.contains("=")) {
                    int j = y.indexOf("=");
                    return y.substring(j + 1, y.length());
                } else {
                    return y;
                }
            }).collect(Collectors.toList());

            String query = collect.get(0);
            List<String> objects = new ArrayList<>();
            for (int i = 1; i < collect.size(); ) {
                String arrayJson =
                        "{\"word\":" + "\"" + collect.get(i) + "\"" + "," + "\"type\":" + "\"" + collect.get(i + 1) + "\"}";
                i += 2;
                objects.add(arrayJson);
            }
            System.out.println(query + ", " + objects.toString());
            System.out.println(collect.size());

        }
    }
}

