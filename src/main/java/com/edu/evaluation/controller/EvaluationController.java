package com.edu.evaluation.controller;

import com.edu.evaluation.entity.StudentInfo;
import com.edu.evaluation.service.StudentService;
import com.edu.evaluation.utils.Result;
import com.edu.evaluation.entity.EvaluationInfo;
import com.edu.evaluation.entity.User;
import com.edu.evaluation.service.EvaluationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/submit")
    public Result<String> submit(@RequestBody List<EvaluationInfo> evaluationList, HttpSession session) {

        User loginUser = (User) session.getAttribute("User");

        if (loginUser == null) {
            return Result.error("登录已失效，请重新登录");
        }

        StudentInfo student = studentService.getStudentById(loginUser.getId());
        if (student == null) {
            return Result.error("未找到您的学生档案，无法评价");
        }

        try {

            evaluationService.saveEvaluationBatch(evaluationList, student.getId());
            return Result.success("评价提交成功！");

        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        } catch (Exception e) {
            // 捕获其他未知数据库异常
            e.printStackTrace();
            return Result.error("系统开小差了，保存失败");
        }
    }
}