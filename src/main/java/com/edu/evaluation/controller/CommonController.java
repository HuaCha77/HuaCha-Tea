package com.edu.evaluation.controller;

import com.edu.evaluation.utils.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Value("${file.upload-path:./uploads}")
    private String uploadPath;

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("请选择要上传的文件");
        }

        try {
            File dir = new File(uploadPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + extension;
            
            Path path = Paths.get(uploadPath, fileName);
            Files.copy(file.getInputStream(), path);

            return Result.success("文件上传成功: " + originalFilename);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传失败: " + e.getMessage());
        }
    }

    @GetMapping("/download/manual")
    public void downloadManual(HttpServletResponse response) {
        // 模拟下载一个系统使用手册（实际项目中应指向真实文件）
        String fileName = "教学评价系统使用手册.pdf";
        response.setContentType("application/pdf");
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            
            // 这里为了演示，创建一个空的PDF流（实际应读取服务器文件）
            OutputStream os = response.getOutputStream();
            os.write("这是模拟的系统手册内容".getBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
