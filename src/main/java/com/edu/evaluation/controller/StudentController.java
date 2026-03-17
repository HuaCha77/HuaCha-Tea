package com.edu.evaluation.controller;


import com.edu.evaluation.entity.StudentInfo;
import com.edu.evaluation.service.ClassService;
import com.edu.evaluation.service.StudentService;
import com.edu.evaluation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;

    @GetMapping("/list")
    public String listPage(Model model) {
        // 1. 获取所有学生
        model.addAttribute("studentList", studentService.getAllStudents());
        // 2. 获取所有班级（用于新增/修改时的下拉框）
        model.addAttribute("classList", classService.getAllClasses());
        return "student/list";
    }

    @PostMapping("/add")
    @ResponseBody
    public Result<String> add(@RequestBody StudentInfo student) {
        studentService.saveStudent(student);
        return Result.success("保存成功");
    }

    // 获取单条学生信息用于回显
    @GetMapping("/get/{id}")
    @ResponseBody
    public Result<StudentInfo> getById(@PathVariable Integer id) {
        StudentInfo student = studentService.getStudentById(id);
        return Result.success(student);
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result<String> delete(@PathVariable Integer id) {
        studentService.removeStudent(id);
        return Result.success("删除成功");
    }
}
