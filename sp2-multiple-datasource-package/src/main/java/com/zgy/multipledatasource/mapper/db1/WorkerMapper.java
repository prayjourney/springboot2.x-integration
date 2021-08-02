package com.zgy.multipledatasource.mapper.db1;

import com.zgy.multipledatasource.pojo.Worker;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author zgy
 * @date 2020/10/20
 * @description
 * @url https://www.cnblogs.com/aizen-sousuke/p/11756279.html
 */
// @Mapper标志Mapper文件, @Repository标志DAO，有了这个就没有注入错误了
@Mapper
@Repository
public interface WorkerMapper {
    @Select("select * from worker where id = #{id}")
    Worker selectWorkerById(@Param("id") Integer id);

    @Delete("delete from worker where id = #{id}")
    int deleteWorkerById(@Param("id") Integer id);

    @Select("select name from worker where id = #{id}")
    String getWorkerNameById(@Param("id") Integer id);
}
