package com.zgy.learn.beanutils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-02-18
 * @modified:
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Animal {
    private int Id;
    private String name;
    private String kind;
    private int age;
    private Date birthday;
}
