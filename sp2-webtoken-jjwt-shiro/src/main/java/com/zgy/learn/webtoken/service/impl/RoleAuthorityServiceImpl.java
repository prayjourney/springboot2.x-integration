package com.zgy.learn.webtoken.service.impl;

import com.zgy.learn.webtoken.mapper.RoleAuthorityDao;
import com.zgy.learn.webtoken.pojo.RoleAuthority;
import com.zgy.learn.webtoken.service.RoleAuthorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RoleAuthority)表服务实现类
 *
 * @author makejava
 * @since 2021-02-01 00:58:22
 */
@Service("roleAuthorityService")
public class RoleAuthorityServiceImpl implements RoleAuthorityService {
    @Resource
    private RoleAuthorityDao roleAuthorityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public RoleAuthority queryById(Integer roleId) {
        return this.roleAuthorityDao.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<RoleAuthority> queryAllByLimit(int offset, int limit) {
        return this.roleAuthorityDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param roleAuthority 实例对象
     * @return 实例对象
     */
    @Override
    public RoleAuthority insert(RoleAuthority roleAuthority) {
        this.roleAuthorityDao.insert(roleAuthority);
        return roleAuthority;
    }

    /**
     * 修改数据
     *
     * @param roleAuthority 实例对象
     * @return 实例对象
     */
    @Override
    public RoleAuthority update(RoleAuthority roleAuthority) {
        this.roleAuthorityDao.update(roleAuthority);
        return this.queryById(roleAuthority.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer roleId) {
        return this.roleAuthorityDao.deleteById(roleId) > 0;
    }
}