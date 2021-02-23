package com.zgy.learn.webtoken.service.impl;

import com.zgy.learn.webtoken.mapper.UserRoleMapper;
import com.zgy.learn.webtoken.pojo.UserRole;
import com.zgy.learn.webtoken.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserRole表服务实现类
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:58:43
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public UserRole queryById(Integer userId) {
        return this.userRoleMapper.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserRole> queryAllByLimit(int offset, int limit) {
        return this.userRoleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserRole> queryAllById(Integer userId) {
        return this.userRoleMapper.queryAllById(userId);
    }

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole insert(UserRole userRole) {
        this.userRoleMapper.insert(userRole);
        return userRole;
    }

    /**
     * 修改数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole update(UserRole userRole) {
        this.userRoleMapper.update(userRole);
        return this.queryById(userRole.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userId) {
        return this.userRoleMapper.deleteById(userId) > 0;
    }
}