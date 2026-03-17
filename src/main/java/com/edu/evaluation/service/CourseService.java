package com.edu.evaluation.service;

import com.edu.evaluation.entity.CourseInfo;
import java.util.List;

public interface CourseService {
    /**
     * 获取所有有效的课程列表
     */
    List<CourseInfo> getAllCourses();

    /**
     * 根据ID查询单条课程信息
     */
    CourseInfo getCourseById(Integer id);

    /**
     * 保存课程（包括新增和修改）
     */
    void saveCourse(CourseInfo course);

    /**
     * 逻辑删除课程
     */
    void removeCourse(Integer id);
}