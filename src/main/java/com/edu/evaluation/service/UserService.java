package com.edu.evaluation.service;
import com.edu.evaluation.entity.User;

public interface UserService {
    User login(String username, String password, String role);
    void save(User user);
    void update(User user);
    void delete(Integer id);
    java.util.List<User> getAll();
    User getById(Integer id);
}