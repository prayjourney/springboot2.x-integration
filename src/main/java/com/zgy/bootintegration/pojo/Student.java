package com.zgy.bootintegration.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/17 21:41
 * @Modified by:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int stId;
    private String stName;
    private int stAge;
    private char stGender;
    private int classId;
    private int hobbyGroupId;
    private String stHome;
    private int cityId;
}
