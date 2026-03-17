package com.edu.evaluation.controller;

import com.edu.evaluation.entity.ClassInfo;
import com.edu.evaluation.service.ClassService;
import com.edu.evaluation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    // 班级列表页面
    @GetMapping("/list")
    public String listPage(Model model) {
        List<ClassInfo> list = classService.getAllClasses();
        model.addAttribute("classList", list);
        return "class/list"; // 指向 templates/class/list.html
    }

    // 处理新增请求
    @PostMapping("/add")
    @ResponseBody
    public Result<String> add(@RequestBody ClassInfo classInfo) {
        classService.addClass(classInfo);
        return Result.success("班级添加成功");
    }

    // 逻辑删除
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result<String> delete(@PathVariable Integer id) {
        classService.removeClass(id);
        return Result.success("删除成功");
    }
}