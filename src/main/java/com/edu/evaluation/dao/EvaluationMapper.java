package com.edu.evaluation.dao;

import com.edu.evaluation.entity.EvaluationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface EvaluationMapper {
    // 核心：批量插入多条评价记录
    int insertBatch(@Param("list") List<EvaluationInfo> list);

    // 检查该学生是否已经对该授课安排进行了评价
    int checkIsEvaluated(@Param("studentId") Integer studentId, @Param("teachingId") Integer teachingId);

    // 获取某次授课的总平均分
    Double getAvgScoreByTeachingId(Integer teachingId);

    // 获取某次授课的各指标评分
    java.util.List<java.util.Map<String, Object>> getIndicatorStatsByTeachingId(Integer teachingId);

    // 获取某次授课的所有评语（匿名）
    java.util.List<String> getCommentsByTeachingId(Integer teachingId);

    // --- 大数据分析专用接口 ---
    
    // 1. 全校平均分
    Double getGlobalAvgScore();
    
    // 2. 总评价次数
    Integer getTotalEvalCount();
    
    // 3. 参与教师总数
    Integer getGlobalTeacherCount();
    
    // 4. 分数分布 (0-60, 60-70, etc.)
    java.util.List<java.util.Map<String, Object>> getGlobalScoreDistribution();
    
    // 5. 学期满意度趋势
    java.util.List<java.util.Map<String, Object>> getSemesterTrend();
    
    // 6. 优秀教师 Top 10
    java.util.List<java.util.Map<String, Object>> getTopTeachers();

    // --- 首页专用统计 ---
    // 学生总任务数
    Integer getStudentTotalTasks(@Param("userId") Integer userId);
    // 学生已完成任务数
    Integer getStudentCompletedTasks(@Param("userId") Integer userId);
}