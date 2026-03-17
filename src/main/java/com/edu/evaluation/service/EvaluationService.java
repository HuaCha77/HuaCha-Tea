package com.edu.evaluation.service;

import com.edu.evaluation.entity.EvaluationInfo;
import java.util.List;

public interface EvaluationService {
    // 保存评价流水
    void saveEvaluationBatch(List<EvaluationInfo> evaluationList, Integer studentId);

    // 判断是否已评价
    boolean hasEvaluated(Integer studentId, Integer teachingId);
}