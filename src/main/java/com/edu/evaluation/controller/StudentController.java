package com.edu.evaluation.controller;


import com.edu.evaluation.entity.StudentInfo;
import com.edu.evaluation.entity.TeachingInfo;
import com.edu.evaluation.entity.User;
import com.edu.evaluation.service.*;
import com.edu.evaluation.utils.Result;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;
    @Autowired
    private TeachingService teachingService;
    @Autowired
    private IndicatorService indicatorService;
    @Autowired
    private EvaluationService evaluationService;

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

    @GetMapping("/my-tasks")
    public String myTasks(HttpSession session, Model model) {
        // 1. 获取当前登录的用户
        User loginUser = (User) session.getAttribute("User");
        if (loginUser == null) {
            return "redirect:/login"; // 没登录就踢回登录页
        }

        // 2. 根据登录的 userId 查出具体的学生信息（为了拿到他的班级ID）
        StudentInfo student = studentService.getStudentById(loginUser.getId());

        if (student != null) {
            // 1. 查出这学期该班级所有的课
            List<TeachingInfo> tasks = teachingService.getTasksByClassId(student.getClassId());

            // 2. 【核心逻辑】遍历每一门课，查一下当前学生是否已经评过了
            for (TeachingInfo task : tasks) {
                // 调用我们之前写好的方法查数据库
                boolean evaluated = evaluationService.hasEvaluated(student.getId(), task.getId());
                // 如果评过了，就把状态设为 1；没评过就是默认的 0
                if (evaluated) {
                    task.setIsFinished(1);
                } else {
                    task.setIsFinished(0);
                }
            }

            // 3. 把处理好的、带状态的数据传给前端
            model.addAttribute("taskList", tasks);
        }

        // 4. 跳转到 src/main/resources/templates/student/my_tasks.html
        return "student/my_tasks";
    }

    // 跳转到打分页面
    @GetMapping("/evaluate/{teachingId}")
    public String evaluatePage(@PathVariable Integer teachingId, Model model) {
        // 1. 查出当前要评价的是哪门课、哪个老师
        model.addAttribute("teaching", teachingService.getById(teachingId));
        // 2. 查出所有的评价指标（用于前端循环渲染）
        model.addAttribute("indicators", indicatorService.getAll());

        return "student/evaluate"; // 对应 templates/student/evaluate.html
    }
}
