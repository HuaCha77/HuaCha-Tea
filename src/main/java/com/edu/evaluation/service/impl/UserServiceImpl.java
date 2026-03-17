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
        // 基础校验：用户是否存在、密码是否匹配、角色是否匹配、账号是否启用
        if (user != null && user.getPassword().equals(password) && 
            user.getRole().equals(role) && user.getStatus() == 0) {
            return user;
        }
        return null;
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public java.util.List<User> getAll() {
        return userMapper.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userMapper.findById(id);
    }

}