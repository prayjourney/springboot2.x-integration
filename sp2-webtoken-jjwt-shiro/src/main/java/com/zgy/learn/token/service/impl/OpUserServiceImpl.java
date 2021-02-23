package com.zgy.learn.token.service.impl;

import com.zgy.learn.token.mapper.OpUserMapper;
import com.zgy.learn.token.pojo.OpUser;
import com.zgy.learn.token.service.OpUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * OpUser表服务实现类
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:58:23
 */
@Service("opUserService")
public class OpUserServiceImpl implements OpUserService {
    @Resource
    private OpUserMapper opUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OpUser queryById(Integer id) {
        return this.opUserMapper.queryById(id);
    }

    @Override
    public OpUser queryByName(String name) {
        return this.opUserMapper.queryByName(name);
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
        return this.opUserMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param opUser 实例对象
     * @return 实例对象
     */
    @Override
    public OpUser insert(OpUser opUser) {
        this.opUserMapper.insert(opUser);
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
        this.opUserMapper.update(opUser);
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
        return this.opUserMapper.deleteById(id) > 0;
    }
}