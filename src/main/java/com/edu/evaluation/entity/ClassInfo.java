package com.edu.evaluation.entity;

/**
 * 班级实体类
 */
public class ClassInfo extends BaseEntity {
    private Integer id;
    private String className;
    private String major;
    private String grade;

    public ClassInfo() {}

    // 手动生成 Getter 和 Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}