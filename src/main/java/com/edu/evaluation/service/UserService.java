package com.edu.evaluation.service;
import com.edu.evaluation.entity.User;

public interface UserService {
    User login(String username, String password, String role);

}