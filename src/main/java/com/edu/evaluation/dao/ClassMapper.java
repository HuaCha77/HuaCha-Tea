package com.edu.evaluation.dao;

import com.edu.evaluation.entity.ClassInfo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ClassMapper {
    // 查询所有未删除的班级
    List<ClassInfo> findAll();

    // 新增班级
    int insert(ClassInfo classInfo);

    // 逻辑删除班级
    int deleteById(Integer id);
}