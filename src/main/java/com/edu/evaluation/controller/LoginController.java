package com.edu.evaluation.controller;

import com.edu.evaluation.entity.User;
import com.edu.evaluation.service.UserService;
import com.edu.evaluation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // 返回 templates/login.html
    }

    @PostMapping("/login")
    @ResponseBody
    public Result<User> login(String username, String password, String role, HttpSession session) {
        User user = userService.login(username, password, role);
        if (user != null) {
            session.setAttribute("User", user);
            return Result.success("登录成功", user);
        }
        return Result.error("用户名、密码或身份错误");
    }

    @GetMapping("/index")
    public String indexPage(HttpSession session) {
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("User");
        session.invalidate(); // 使整个 Session 失效
        return "redirect:/login";
    }
}