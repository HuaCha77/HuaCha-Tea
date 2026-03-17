package com.edu.evaluation.config;

import com.edu.evaluation.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        // 1. 登录校验
        if (user == null) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/login");
            return false;
        }

        // 2. 权限校验 (简单实现：根据路径前缀校验角色)
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String relativeUri = uri.substring(contextPath.length());

        // 管理员拥有全量访问权限
        if ("ADMIN".equals(user.getRole())) {
            return true;
        }

        // 学生权限
        if (relativeUri.startsWith("/student") || relativeUri.startsWith("/evaluation")) {
            if (!"STUDENT".equals(user.getRole())) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "无权访问该功能");
                return false;
            }
            return true;
        }

        // 教师权限
        if (relativeUri.startsWith("/teacher") && !"TEACHER".equals(user.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "无权访问教师功能");
            return false;
        }

        // 统计权限 (管理员和教师均可访问)
        if ((relativeUri.startsWith("/stats") || relativeUri.startsWith("/api/stats")) && 
            !"TEACHER".equals(user.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "无权访问统计功能");
            return false;
        }

        // 个人中心权限 (所有登录用户均可访问)
        if (relativeUri.equals("/user/profile") || relativeUri.equals("/user/password")) {
            return true;
        }

        // 其他功能 (用户管理、指标、班级、课程、授课等) 默认仅管理员访问
        // 由于上面已经 return true 了管理员，这里非管理员全部拦截
        if (relativeUri.startsWith("/user") || relativeUri.startsWith("/indicator") || 
            relativeUri.startsWith("/class") || relativeUri.startsWith("/course") || 
            relativeUri.startsWith("/teaching") || relativeUri.startsWith("/evaluation")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "无权访问该功能模块");
            return false;
        }

        return true;
    }
}
