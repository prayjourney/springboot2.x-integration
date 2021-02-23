package com.zgy.bootintegration.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/5/17 21:41
 * @modified:
 */
@Setter
@Getter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
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
