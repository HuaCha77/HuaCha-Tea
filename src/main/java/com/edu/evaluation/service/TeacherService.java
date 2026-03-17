package com.edu.evaluation.service;
import com.edu.evaluation.entity.TeacherInfo;

import java.util.List;

public interface TeacherService {
    List<TeacherInfo> getAllTeachers();
    void saveTeacher(TeacherInfo teacher);
    void removeTeacher(Integer id);
    TeacherInfo getTeacherById(Integer id);

}
