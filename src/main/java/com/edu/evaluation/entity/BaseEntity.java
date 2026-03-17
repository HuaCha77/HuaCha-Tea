package com.edu.evaluation.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类 - 原生版
 */
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
    private Integer delFlag; // 0：正常，1：已删除

    // --- 手动生成的 Getter 和 Setter ---
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    public String getCreateBy() { return createBy; }
    public void setCreateBy(String createBy) { this.createBy = createBy; }

    public String getUpdateBy() { return updateBy; }
    public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }

    public Integer getDelFlag() { return delFlag; }
    public void setDelFlag(Integer delFlag) { this.delFlag = delFlag; }
}