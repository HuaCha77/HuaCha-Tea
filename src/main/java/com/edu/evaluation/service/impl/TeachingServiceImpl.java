package com.edu.evaluation.service.impl;

import com.edu.evaluation.dao.TeachingMapper;
import com.edu.evaluation.entity.TeacherInfo;
import com.edu.evaluation.entity.TeachingInfo;
import com.edu.evaluation.service.TeachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachingServiceImpl implements TeachingService {
    @Autowired
    private TeachingMapper teachingMapper;

    @Override
    public List<TeachingInfo> getAllTeaching(){
        return teachingMapper.findAll();
    }

    @Override
    public TeachingInfo getById(Integer id) {
        return teachingMapper.findById(id);
    }

    @Override
    public void saveTeaching(TeachingInfo teachingInfo) {
        teachingMapper.insert(teachingInfo);
    }

    @Override
    public void removeTeaching(Integer id) {
        teachingMapper.deleteById(id);
    }


}
