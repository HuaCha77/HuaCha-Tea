package com.edu.evaluation.utils;

import java.io.Serializable;

/**
 * 统一返回结果类 - 原生实现版（无需 Lombok 插件）
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;    // 状态码
    private String message;  // 提示消息
    private T data;          // 返回数据

    public Result() {}

    // --- 静态工厂方法 ---
    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setCode(400);
        result.setMessage(message);
        return result;
    }

    // --- 手动生成 Getter 和 Setter (这是解决“找不到符号”的关键) ---
    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}