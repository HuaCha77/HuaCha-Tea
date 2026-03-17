package com.edu.evaluation.entity;

/**
 * 用户实体类 - 原生版
 */
public class User extends BaseEntity {
    private Integer id;
    private String username;
    private String password;
    private String role;   // admin, teacher, student
    private Integer status; // 0:正常, 1:停用

    public User() {}

    // --- 手动生成的 Getter 和 Setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}