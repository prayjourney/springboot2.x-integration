package org.zgy.myschool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zgy.myschool.mapper.CityMapper;
import org.zgy.myschool.pojo.City;
import org.zgy.myschool.service.CitySevice;

/**
 * @author: zgy
 * @since: 2020-05-19
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CitySevice {

}
