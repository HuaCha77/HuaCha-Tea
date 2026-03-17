package com.edu.evaluation.dao;

import com.edu.evaluation.entity.IndicatorInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndicatorMapper {
    List<IndicatorInfo> findAll();
    IndicatorInfo findById(Integer id);
    int insert(IndicatorInfo indicator);
    int update(IndicatorInfo indicator);
    int deleteById(Integer id);
}
