package com.zgy.learn.token.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: z.g.y
 * @since: 2021-02-01 00:58:23
 */

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Message implements Serializable {
    private static final long serialVersionUID = 228323853100971137L;

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * message内容
     */
    private String content;

    /**
     * 建立时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
