package com.edu.evaluation.service;
import com.edu.evaluation.entity.StudentInfo;

import java.util.List;

public interface StudentService {
    List<StudentInfo> getAllStudents();
    void saveStudent(StudentInfo student);
    void removeStudent(Integer id);
    StudentInfo getStudentById(Integer id);

}