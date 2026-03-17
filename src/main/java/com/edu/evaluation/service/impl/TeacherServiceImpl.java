package com.edu.evaluation.service.impl;


import com.edu.evaluation.dao.TeacherMapper;
import com.edu.evaluation.dao.UserMapper;
import com.edu.evaluation.entity.TeacherInfo;
import com.edu.evaluation.entity.User;
import com.edu.evaluation.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<TeacherInfo> getAllTeachers() {
        return teacherMapper.findAll();
    }

    @Override
    @Transactional // 开启事务，确保两个表的操作要么同时成功，要么同时失败
    public void saveTeacher(TeacherInfo teacher) {
        if (teacher.getId() == null) {
            // 1. 同步创建一个系统用户账号
            User user = new User();
            // 核心修改：将教师工号设置为登录用户名
            user.setUsername(teacher.getTeacherNo());
            user.setPassword("123456"); // 设置初始默认密码
            user.setRole("TEACHER");    // 设置角色为老师

            // 2. 执行用户插入，获取生成的 id
            userMapper.insert(user);

            // 3. 将生成的 userId 绑定到教师信息中
            teacher.setUserId(user.getId());

            // 4. 保存教师基础信息
            teacherMapper.insert(teacher);
        } else {
            teacherMapper.update(teacher);
        }
    }

    @Override
    public TeacherInfo getTeacherById(Integer id) {
        return teacherMapper.findById(id);
    }

    @Override
    public void removeTeacher(Integer id) {
        teacherMapper.deleteById(id);
    }
}