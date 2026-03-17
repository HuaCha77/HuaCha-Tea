package com.edu.evaluation.dao;

import com.edu.evaluation.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    // 根据用户名查询用户信息
    User findByUsername(@Param("username") String username);
    int insert(User user);
}