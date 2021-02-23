package com.zgy.learn.token.service;

import com.zgy.learn.token.pojo.OpUser;

import java.util.List;

/**
 * OpUser表服务接口
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:58:23
 */
public interface OpUserService {

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
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OpUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param opUser 实例对象
     * @return 实例对象
     */
    OpUser insert(OpUser opUser);

    /**
     * 修改数据
     *
     * @param opUser 实例对象
     * @return 实例对象
     */
    OpUser update(OpUser opUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}