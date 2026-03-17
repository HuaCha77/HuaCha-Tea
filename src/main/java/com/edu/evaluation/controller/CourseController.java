package com.edu.evaluation.controller;

import com.edu.evaluation.entity.CourseInfo;
import com.edu.evaluation.service.CourseService;
import com.edu.evaluation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public String listPage(Model model) {
        model.addAttribute("courseList", courseService.getAllCourses());
        return "course/list";
    }

    @PostMapping("/save")
    @ResponseBody
    public Result<String> save(@RequestBody CourseInfo course) {
        courseService.saveCourse(course);
        return Result.success("操作成功"); // 使用 Result 工具类
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Result<CourseInfo> getById(@PathVariable Integer id) {
        CourseInfo course = courseService.getCourseById(id); // 需在 Service 中补充此方法
        return Result.success(course);
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result<String> delete(@PathVariable Integer id) {
        courseService.removeCourse(id);
        return Result.success("删除成功");
    }
}
