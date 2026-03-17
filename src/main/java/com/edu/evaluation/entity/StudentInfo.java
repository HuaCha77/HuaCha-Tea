package com.edu.evaluation.entity;

/**
 * 学生信息实体类
 */
public class StudentInfo extends BaseEntity {
    private Integer id;
    private Integer userId;    // 关联用户ID
    private Integer classId;   // 关联班级ID
    private String studentNo;  // 学号
    private String name;       // 姓名
    private String gender;     // 性别

    // 冗余字段：用于在列表页面直接显示班级名称，不需要在数据库存
    private String className;

    public StudentInfo() {}

    // 手动生成 Getter 和 Setter (略，请务必补全)
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getClassId() { return classId; }
    public void setClassId(Integer classId) { this.classId = classId; }
    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
}