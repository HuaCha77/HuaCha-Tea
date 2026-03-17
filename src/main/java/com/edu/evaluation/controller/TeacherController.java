package com.edu.evaluation.controller;

import com.edu.evaluation.entity.TeacherInfo;
import com.edu.evaluation.service.TeacherService;
import com.edu.evaluation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public String listPage(Model model) {
        model.addAttribute("teacherList", teacherService.getAllTeachers());
        return "teacher/list";
    }

    @PostMapping("/save")
    @ResponseBody
    public Result<String> save(@RequestBody TeacherInfo teacher) {
        teacherService.saveTeacher(teacher);
        return Result.success("操作成功"); // 使用 Result 工具类
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Result<TeacherInfo> getById(@PathVariable Integer id) {
        TeacherInfo teacher = teacherService.getTeacherById(id); // 需在 Service 中补充此方法
        return Result.success(teacher);
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result<String> delete(@PathVariable Integer id) {
        teacherService.removeTeacher(id);
        return Result.success("删除成功");
    }
}
