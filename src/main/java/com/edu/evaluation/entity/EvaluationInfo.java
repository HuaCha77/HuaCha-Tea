package com.edu.evaluation.entity;

import java.util.Date;

public class EvaluationInfo {
    private Integer id;
    private Integer studentId;    // 评价学生ID
    private Integer teachingId;   // 授课安排ID
    private Integer indicatorId;  // 指标ID
    private Double score;         // 打分（用Double方便后续数学计算）
    private String comment;       // 评语
    private Date createTime;

    // --- 请手动生成以上所有属性的 Getter 和 Setter ---

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public Integer getTeachingId() { return teachingId; }
    public void setTeachingId(Integer teachingId) { this.teachingId = teachingId; }
    public Integer getIndicatorId() { return indicatorId; }
    public void setIndicatorId(Integer indicatorId) { this.indicatorId = indicatorId; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}