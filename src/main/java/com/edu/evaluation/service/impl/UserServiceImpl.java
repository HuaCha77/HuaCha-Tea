package com.edu.evaluation.service.impl;

import com.edu.evaluation.dao.UserMapper;
import com.edu.evaluation.entity.User;
import com.edu.evaluation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password, String role) {
        User user = userMapper.findByUsername(username);
        // 基础校验：用户是否存在、密码是否匹配、角色是否匹配
        if (user != null && user.getPassword().equals(password) && user.getRole().equals(role)) {
            return user;
        }
        return null;
    }

}