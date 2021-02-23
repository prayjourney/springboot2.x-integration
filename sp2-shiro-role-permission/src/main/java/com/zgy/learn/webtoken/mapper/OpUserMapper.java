package com.zgy.learn.webtoken.mapper;

import com.zgy.learn.webtoken.pojo.OpUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OpUser表数据库访问层
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:58:43
 */
@Mapper
@Repository
public interface OpUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OpUser queryById(Integer id);

    /**
     * 通过name查询单条数据
     *
     * @param name
     * @return
     */
    OpUser queryByName(String name);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OpUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param opUser 实例对象
     * @return 对象列表
     */
    List<OpUser> queryAll(OpUser opUser);

    /**
     * 新增数据
     *
     * @param opUser 实例对象
     * @return 影响行数
     */
    int insert(OpUser opUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OpUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OpUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OpUser> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<OpUser> entities);

    /**
     * 修改数据
     *
     * @param opUser 实例对象
     * @return 影响行数
     */
    int update(OpUser opUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}