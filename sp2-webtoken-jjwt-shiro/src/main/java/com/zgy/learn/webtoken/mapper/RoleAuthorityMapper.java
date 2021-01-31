package com.zgy.learn.webtoken.mapper;

import com.zgy.learn.webtoken.pojo.RoleAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (RoleAuthority)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-01 00:58:22
 */
public interface RoleAuthorityMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    RoleAuthority queryById(Integer roleId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RoleAuthority> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param roleAuthority 实例对象
     * @return 对象列表
     */
    List<RoleAuthority> queryAll(RoleAuthority roleAuthority);

    /**
     * 新增数据
     *
     * @param roleAuthority 实例对象
     * @return 影响行数
     */
    int insert(RoleAuthority roleAuthority);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RoleAuthority> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RoleAuthority> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RoleAuthority> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<RoleAuthority> entities);

    /**
     * 修改数据
     *
     * @param roleAuthority 实例对象
     * @return 影响行数
     */
    int update(RoleAuthority roleAuthority);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Integer roleId);

}