package com.example.dao;

import com.example.entity.Params;
import com.example.entity.Symptom;
import com.example.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserDao extends Mapper<User> {
    List<User> findBySearch(@Param("params") Params params);
    @Select("select * from user where id = #{id} limit 1")
    User findById(@Param("id")String id);

    @Select("select * from user where id = #{id} and password = #{password} limit 1")
    User findByIdAndPassword(@Param("id")String Id,@Param("password")String password);
}


