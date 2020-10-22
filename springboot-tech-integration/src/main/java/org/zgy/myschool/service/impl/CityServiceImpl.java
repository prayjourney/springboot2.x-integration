package org.zgy.myschool.service.impl;

import org.zgy.myschool.pojo.City;
import org.zgy.myschool.mapper.CityMapper;
import org.zgy.myschool.service.CitySevice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zgy
 * @since 2020-05-19
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CitySevice {

}
