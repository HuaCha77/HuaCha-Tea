package com.edu.evaluation.service;
import com.edu.evaluation.entity.ClassInfo;
import java.util.List;

public interface ClassService {
    List<ClassInfo> getAllClasses();
    void addClass(ClassInfo classInfo);
    void removeClass(Integer id);
    ClassInfo getClassById(Integer id);
    void updateClass(ClassInfo classInfo);
}