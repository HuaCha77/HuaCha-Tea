package com.edu.evaluation.controller;

import com.edu.evaluation.entity.TeachingInfo;
import com.edu.evaluation.service.*;
import com.edu.evaluation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teaching")
public class TeachingController {
    @Autowired private TeachingService teachingService;
    @Autowired private TeacherService teacherService;
    @Autowired private CourseService courseService;
    @Autowired private ClassService classService;

    @GetMapping("/list")
    public String listPage(Model model) {
        // 1. 这里的变量名必须和 HTML 里的 th:each="t : ${teachingList}" 对应
        model.addAttribute("teachingList", teachingService.getAllTeaching());

        // 2. 这里的变量名必须和 HTML 下拉框里的 ${teacherList} 对应
        model.addAttribute("teacherList", teacherService.getAllTeachers());

        // 3. 这里的变量名必须和 HTML 下拉框里的 ${courseList} 对应
        model.addAttribute("courseList", courseService.getAllCourses());

        // 4. 这里的变量名必须和 HTML 下拉框里的 ${classList} 对应
        model.addAttribute("classList", classService.getAllClasses());

        return "teaching/list";
    }

    // 不要忘记 getById 方法，否则修改功能会失灵
    @GetMapping("/get/{id}")
    @ResponseBody
    public Result<TeachingInfo> getById(@PathVariable Integer id) {
        return Result.success(teachingService.getById(id));
    }

    @PostMapping("/save")
    @ResponseBody
    public Result<String> save(@RequestBody TeachingInfo teachingInfo) {
        teachingService.saveTeaching(teachingInfo);
        return Result.success("添加成功");
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public  Result<String> delete(@PathVariable Integer id) {
        teachingService.removeTeaching(id);
        return Result.success("删除成功");
    }
}
