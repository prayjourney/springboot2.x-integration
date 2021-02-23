package com.zgy.learn.token.mapper;

import com.zgy.learn.token.pojo.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Authority表数据库访问层
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:58:23
 */
@Mapper
@Repository
public interface AuthorityMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Authority queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Authority> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param authority 实例对象
     * @return 对象列表
     */
    List<Authority> queryAll(Authority authority);

    /**
     * 新增数据
     *
     * @param authority 实例对象
     * @return 影响行数
     */
    int insert(Authority authority);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Authority> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Authority> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Authority> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Authority> entities);

    /**
     * 修改数据
     *
     * @param authority 实例对象
     * @return 影响行数
     */
    int update(Authority authority);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}