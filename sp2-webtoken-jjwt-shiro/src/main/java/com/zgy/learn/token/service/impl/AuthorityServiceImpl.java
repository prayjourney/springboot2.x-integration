package com.zgy.learn.token.service.impl;

import com.zgy.learn.token.mapper.AuthorityMapper;
import com.zgy.learn.token.pojo.Authority;
import com.zgy.learn.token.service.AuthorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Authority表服务实现类
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:58:23
 */
@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {
    @Resource
    private AuthorityMapper authorityMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Authority queryById(Integer id) {
        return this.authorityMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Authority> queryAllByLimit(int offset, int limit) {
        return this.authorityMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    @Override
    public Authority insert(Authority authority) {
        this.authorityMapper.insert(authority);
        return authority;
    }

    /**
     * 修改数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    @Override
    public Authority update(Authority authority) {
        this.authorityMapper.update(authority);
        return this.queryById(authority.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.authorityMapper.deleteById(id) > 0;
    }
}