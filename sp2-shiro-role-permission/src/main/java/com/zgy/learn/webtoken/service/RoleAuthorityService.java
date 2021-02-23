package com.zgy.learn.webtoken.service;

import com.zgy.learn.webtoken.pojo.RoleAuthority;

import java.util.List;

/**
 * RoleAuthority表服务接口
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:58:43
 */
public interface RoleAuthorityService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    RoleAuthority queryById(Integer roleId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RoleAuthority> queryAllByLimit(int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param roleId
     * @return
     */
    List<RoleAuthority> queryAllById(Integer roleId);

    /**
     * 新增数据
     *
     * @param roleAuthority 实例对象
     * @return 实例对象
     */
    RoleAuthority insert(RoleAuthority roleAuthority);

    /**
     * 修改数据
     *
     * @param roleAuthority 实例对象
     * @return 实例对象
     */
    RoleAuthority update(RoleAuthority roleAuthority);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer roleId);

}