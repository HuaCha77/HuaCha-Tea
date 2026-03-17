package com.edu.evaluation.service.impl;


import com.edu.evaluation.dao.StudentMapper;
import com.edu.evaluation.dao.UserMapper;
import com.edu.evaluation.entity.StudentInfo;
import com.edu.evaluation.entity.User;
import com.edu.evaluation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<StudentInfo> getAllStudents() {
        return studentMapper.findAll();
    }

    @Override
    @Transactional // 开启事务，确保两个表的操作要么同时成功，要么同时失败
    public void saveStudent(StudentInfo student) {
        if (student.getId() == null) {
            // 1. 同步创建一个系统用户账号
            User user = new User();
            // 核心修改：将教师工号设置为登录用户名
            user.setUsername(student.getStudentNo());
            user.setPassword("123456"); // 设置初始默认密码
            user.setRole("STUDENT");    // 设置角色为老师

            // 2. 执行用户插入，获取生成的 id
            userMapper.insert(user);

            // 3. 将生成的 userId 绑定到教师信息中
            student.setUserId(user.getId());

            // 4. 保存教师基础信息
            studentMapper.insert(student);
        } else {
            studentMapper.update(student);
        }
    }

    @Override
    public StudentInfo getStudentById(Integer id) {
        return studentMapper.findById(id);
    }

    @Override
    public void removeStudent(Integer id) {
        studentMapper.deleteById(id);
    }
}