package com.edu.evaluation.controller;

import com.edu.evaluation.dao.EvaluationMapper;
import com.edu.evaluation.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        // 获取当前登录用户
        User user = (User) session.getAttribute("User");

        if (user == null) {
            return "redirect:/login"; // 未登录则跳转到登录页
        }

        // 根据角色获取不同数据
        if ("STUDENT".equals(user.getRole())) {
            Integer totalTasks = evaluationMapper.getStudentTotalTasks(user.getId());
            Integer completedTasks = evaluationMapper.getStudentCompletedTasks(user.getId());
            
            model.addAttribute("totalTasks", totalTasks != null ? totalTasks : 0);
            model.addAttribute("completedTasks", completedTasks != null ? completedTasks : 0);
            model.addAttribute("pendingTasks", (totalTasks != null ? totalTasks : 0) - (completedTasks != null ? completedTasks : 0));
        } else {
            // 管理员和教师
            Double globalAvg = evaluationMapper.getGlobalAvgScore();
            Integer totalEval = evaluationMapper.getTotalEvalCount();
            
            model.addAttribute("globalAvgScore", globalAvg != null ? globalAvg : 0.0);
            model.addAttribute("totalEvalCount", totalEval != null ? totalEval : 0);
        }

        return "index";
    }
}