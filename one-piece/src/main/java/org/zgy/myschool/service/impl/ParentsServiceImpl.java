package org.zgy.myschool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zgy.myschool.mapper.ParentsMapper;
import org.zgy.myschool.pojo.Parents;
import org.zgy.myschool.service.ParentsSevice;

/**
 * @author: zgy
 * @since: 2020-05-19
 */
@Service
public class ParentsServiceImpl extends ServiceImpl<ParentsMapper, Parents> implements ParentsSevice {

}
