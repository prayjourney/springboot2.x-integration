package com.zgy.multipledatasource.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author renjiaxin
 * @Date 2020/10/20
 * @Description
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Scenery {
    /**
     * 景色id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 国家
     */
    private String country;

    /**
     * 城市
     */
    private String city;

    /**
     * 面积
     */
    private Integer area;

    /**
     * 地址
     */
    private String addr;
}
