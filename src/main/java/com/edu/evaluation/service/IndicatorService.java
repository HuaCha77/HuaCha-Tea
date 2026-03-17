package com.edu.evaluation.service;

import com.edu.evaluation.entity.IndicatorInfo;

import java.util.List;

public interface IndicatorService {
    List<IndicatorInfo> getAll();
    IndicatorInfo getById(Integer id);
    void save(IndicatorInfo indicator);
    void delete(Integer id);
}
