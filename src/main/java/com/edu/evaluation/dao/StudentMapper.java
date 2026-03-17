package com.edu.evaluation.dao;


import com.edu.evaluation.entity.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<StudentInfo> findAll();
    int insert(StudentInfo student);
    int update(StudentInfo student);
    int deleteById(Integer id);
    StudentInfo findById(Integer id);
}
