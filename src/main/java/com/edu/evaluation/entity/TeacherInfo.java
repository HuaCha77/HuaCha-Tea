package com.edu.evaluation.entity;

/**
 * 教师信息实体类
 */
public class TeacherInfo extends BaseEntity {
    private Integer id;
    private Integer userId;    // 关联用户ID
    private String teacherNo;  // 工号
    private String name;       // 姓名
    private String gender;     // 性别
    private String contact;    // 联系方式
    private String title;      // 职称

    public TeacherInfo() {}

    // 手动生成 Getter 和 Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getTeacherNo() { return teacherNo; }
    public void setTeacherNo(String teacherNo) { this.teacherNo = teacherNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}