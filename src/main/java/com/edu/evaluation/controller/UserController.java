package com.edu.evaluation.controller;

import com.edu.evaluation.entity.User;
import com.edu.evaluation.service.UserService;
import com.edu.evaluation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/import")
    @ResponseBody
    public Result<String> importUsers(@RequestParam("file") MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        List<User> users = new ArrayList<>();
        
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;
            
            User user = new User();
            user.setUsername(row.getCell(0).getStringCellValue());
            user.setPassword("123456"); // 初始密码
            user.setRole(row.getCell(1).getStringCellValue());
            user.setStatus(0);
            users.add(user);
        }
        
        for (User u : users) {
            userService.save(u);
        }
        
        workbook.close();
        return Result.success("成功导入 " + users.size() + " 个用户");
    }

    @GetMapping("/list")
    public String listPage(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "user/list";
    }

    @GetMapping("/api/list")
    @ResponseBody
    public Result<List<User>> list() {
        return Result.success(userService.getAll());
    }

    @PostMapping("/add")
    @ResponseBody
    public Result<String> add(@RequestBody User user) {
        userService.save(user);
        return Result.success("用户添加成功");
    }

    @PostMapping("/update")
    @ResponseBody
    public Result<String> update(@RequestBody User user) {
        userService.update(user);
        return Result.success("用户信息更新成功");
    }

    @PostMapping("/status")
    @ResponseBody
    public Result<String> changeStatus(Integer id, Integer status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        userService.update(user);
        return Result.success("状态修改成功");
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result<String> delete(@PathVariable Integer id) {
        userService.delete(id);
        return Result.success("用户已删除");
    }

    @PostMapping("/password")
    @ResponseBody
    public Result<String> updatePassword(String oldPassword, String newPassword, jakarta.servlet.http.HttpSession session) {
        User user = (User) session.getAttribute("User");
        if (user == null) return Result.error("未登录");
        
        User dbUser = userService.getById(user.getId());
        if (!dbUser.getPassword().equals(oldPassword)) {
            return Result.error("原密码错误");
        }
        
        dbUser.setPassword(newPassword);
        userService.update(dbUser);
        session.setAttribute("User", dbUser);
        return Result.success("密码修改成功");
    }

    @PostMapping("/profile")
    @ResponseBody
    public Result<String> updateProfile(@RequestBody User user, jakarta.servlet.http.HttpSession session) {
        User loginUser = (User) session.getAttribute("User");
        if (loginUser == null) return Result.error("未登录");
        
        user.setId(loginUser.getId());
        user.setUsername(null); // 不允许修改用户名/工号
        user.setRole(null);     // 不允许自行修改角色
        
        userService.update(user);
        
        // 更新 Session
        User updated = userService.getById(loginUser.getId());
        session.setAttribute("User", updated);
        
        return Result.success("个人信息更新成功");
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Result<User> get(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "user/profile";
    }

    @GetMapping("/password")
    public String passwordPage() {
        return "user/password";
    }
}
