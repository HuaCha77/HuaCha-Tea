package com.edu.evaluation.service.impl;

import com.edu.evaluation.dao.ClassMapper;
import com.edu.evaluation.entity.ClassInfo;
import com.edu.evaluation.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<ClassInfo> getAllClasses() {
        return classMapper.findAll();
    }

    @Override
    public void addClass(ClassInfo classInfo) {
        classMapper.insert(classInfo);
    }

    @Override
    public void removeClass(Integer id) {
        classMapper.deleteById(id);
    }

    @Override
    public ClassInfo getClassById(Integer id) {
        return classMapper.findById(id);
    }

    @Override
    public void updateClass(ClassInfo classInfo) {
        classMapper.update(classInfo);
    }
}