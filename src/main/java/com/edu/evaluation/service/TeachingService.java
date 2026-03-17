package com.edu.evaluation.service;

import com.edu.evaluation.entity.TeachingInfo;

import java.util.List;

public interface TeachingService {
    List<TeachingInfo> getAllTeaching();
    TeachingInfo getById(Integer id);
    void saveTeaching(TeachingInfo teaching);
    void removeTeaching(Integer id);

}
