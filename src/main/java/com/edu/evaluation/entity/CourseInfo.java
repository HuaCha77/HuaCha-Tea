package com.edu.evaluation.entity;

/**
 * 课程信息实体类
 */
public class CourseInfo extends BaseEntity {
    private Integer id;
    private String courseCode; // 课程代码（如 CS101）
    private String courseName; // 课程名称
    private Double credit;     // 学分（数学系毕设建议用 Double 方便计算）
    private String courseType; // 必修/选修
    private String semester;

    public CourseInfo() {}

    // 手动生成 Getter 和 Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public Double getCredit() { return credit; }
    public void setCredit(Double credit) { this.credit = credit; }
    public String getCourseType() { return courseType; }
    public void setCourseType(String courseType) { this.courseType = courseType; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
}