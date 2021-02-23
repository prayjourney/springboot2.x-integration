package com.zgy.learn.webtoken.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author: z.g.y
 * @date: 2021/1/13
 * @description:
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Kid {
    Integer id;
    String username;
    String password;
}
