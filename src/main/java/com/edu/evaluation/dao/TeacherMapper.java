package com.edu.evaluation.dao;

import com.edu.evaluation.entity.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TeacherMapper {
    List<TeacherInfo> findAll();
    TeacherInfo findById(Integer id);
    int insert(TeacherInfo teacher);
    int update(TeacherInfo teacher);
    int deleteById(Integer id);
}