package com.zgy.learn.webtoken.service.impl;

import com.zgy.learn.webtoken.mapper.OpUserDao;
import com.zgy.learn.webtoken.pojo.OpUser;
import com.zgy.learn.webtoken.service.OpUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (OpUser)表服务实现类
 *
 * @author makejava
 * @since 2021-02-01 00:57:55
 */
@Service("opUserService")
public class OpUserServiceImpl implements OpUserService {
    @Resource
    private OpUserDao opUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OpUser queryById(Integer id) {
        return this.opUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<OpUser> queryAllByLimit(int offset, int limit) {
        return this.opUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param opUser 实例对象
     * @return 实例对象
     */
    @Override
    public OpUser insert(OpUser opUser) {
        this.opUserDao.insert(opUser);
        return opUser;
    }

    /**
     * 修改数据
     *
     * @param opUser 实例对象
     * @return 实例对象
     */
    @Override
    public OpUser update(OpUser opUser) {
        this.opUserDao.update(opUser);
        return this.queryById(opUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.opUserDao.deleteById(id) > 0;
    }
}