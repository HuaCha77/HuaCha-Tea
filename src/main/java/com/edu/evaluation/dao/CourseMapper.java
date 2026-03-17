package com.edu.evaluation.dao;

import com.edu.evaluation.entity.CourseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<CourseInfo> findAll();
    CourseInfo findById(Integer id);
    int insert(CourseInfo course);
    int update(CourseInfo course);
    int deleteById(Integer id);
}
