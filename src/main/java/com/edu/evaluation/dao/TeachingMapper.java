package com.edu.evaluation.dao;

import com.edu.evaluation.entity.TeachingInfo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TeachingMapper {
    List<TeachingInfo> findAll();
    TeachingInfo findById(Integer id);
    int insert(TeachingInfo teaching);
    int update(TeachingInfo teaching);
    int deleteById(Integer id);

    List<TeachingInfo> getTasksByClassId(Integer id);
}