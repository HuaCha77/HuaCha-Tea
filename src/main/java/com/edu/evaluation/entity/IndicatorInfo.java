package com.edu.evaluation.entity;

import java.util.Date;

public class IndicatorInfo extends BaseEntity {
    private Integer id;
    private String indicatorName; // 指标名称
    private String category;      // 分类
    private Double weight;        // 权重 (0-1)
    private Integer maxScore;     // 最高分

    public IndicatorInfo() {}

    // 手动生成 Getter 和 Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getIndicatorName() { return indicatorName; }
    public void setIndicatorName(String indicatorName) { this.indicatorName = indicatorName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    public Integer getMaxScore() { return maxScore; }
    public void setMaxScore(Integer maxScore) { this.maxScore = maxScore; }
}