package com.edu.evaluation.controller;

import com.edu.evaluation.dao.EvaluationMapper;
import com.edu.evaluation.entity.TeachingInfo;
import com.edu.evaluation.service.TeachingService;
import com.edu.evaluation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private TeachingService teachingService;

    @GetMapping("/export/{teachingId}")
    public void exportEvaluation(@PathVariable Integer teachingId, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> stats = evaluationMapper.getIndicatorStatsByTeachingId(teachingId);
        List<String> comments = evaluationMapper.getCommentsByTeachingId(teachingId);
        TeachingInfo teaching = teachingService.getById(teachingId);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("评价统计");

        // 表头
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("指标名称");
        header.createCell(1).setCellValue("平均分");

        int rowNum = 1;
        for (Map<String, Object> stat : stats) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(stat.get("name").toString());
            row.createCell(1).setCellValue(Double.parseDouble(stat.get("score").toString()));
        }

        // 评语页
        Sheet commentSheet = workbook.createSheet("学生评语");
        Row cHeader = commentSheet.createRow(0);
        cHeader.createCell(0).setCellValue("评语内容（匿名）");
        
        rowNum = 1;
        for (String comment : comments) {
            Row row = commentSheet.createRow(rowNum++);
            row.createCell(0).setCellValue(comment);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String fileName = URLEncoder.encode(teaching.getTeacherName() + "-" + teaching.getCourseName() + "-评价报表.xlsx", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @GetMapping("/teaching/{teachingId}")
    public Result<Map<String, Object>> getTeachingStats(@PathVariable Integer teachingId) {
        Map<String, Object> stats = new HashMap<>();
        
        Double avgScore = evaluationMapper.getAvgScoreByTeachingId(teachingId);
        List<Map<String, Object>> indicatorStats = evaluationMapper.getIndicatorStatsByTeachingId(teachingId);
        List<String> comments = evaluationMapper.getCommentsByTeachingId(teachingId);
        
        stats.put("avgScore", avgScore);
        stats.put("indicatorStats", indicatorStats);
        stats.put("comments", comments);
        
        return Result.success(stats);
    }

    @GetMapping("/global")
    public Result<Map<String, Object>> getGlobalStats() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalAvgScore", evaluationMapper.getGlobalAvgScore());
        stats.put("totalEvalCount", evaluationMapper.getTotalEvalCount());
        stats.put("teacherCount", evaluationMapper.getGlobalTeacherCount());
        stats.put("scoreDistribution", evaluationMapper.getGlobalScoreDistribution());
        stats.put("semesterTrend", evaluationMapper.getSemesterTrend());
        stats.put("topTeachers", evaluationMapper.getTopTeachers());
        
        return Result.success(stats);
    }
}
