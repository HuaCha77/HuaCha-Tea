package com.edu.evaluation.service.impl;

import com.edu.evaluation.dao.CourseMapper;
import com.edu.evaluation.entity.CourseInfo;
import com.edu.evaluation.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseInfo> getAllCourses() {
        return courseMapper.findAll();
    }

    @Override
    public CourseInfo getCourseById(Integer id) {
        return courseMapper.findById(id);
    }

    @Override
    public void saveCourse(CourseInfo course) {
        if (course.getId() == null) {
            // 新增逻辑：此处可以扩展，例如检查课程代码(CourseCode)是否已存在
            courseMapper.insert(course);
        } else {
            // 修改逻辑
            courseMapper.update(course);
        }
    }

    @Override
    public void removeCourse(Integer id) {
        // 执行逻辑删除
        courseMapper.deleteById(id);
    }
}