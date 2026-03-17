package com.edu.evaluation.service.impl;

import com.edu.evaluation.dao.EvaluationMapper;
import com.edu.evaluation.entity.EvaluationInfo;
import com.edu.evaluation.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class) // 开启数据库事务
    public void saveEvaluationBatch(List<EvaluationInfo> evaluationList, Integer studentId) {
        if (evaluationList == null || evaluationList.isEmpty()) {
            throw new RuntimeException("评价数据不能为空");
        }

        // 获取这批评价是针对哪一门课的（取第一条的 teachingId 即可）
        Integer teachingId = evaluationList.get(0).getTeachingId();

        // 再次校验防刷单：如果已经评价过，直接抛出异常阻止插入
        if (hasEvaluated(studentId, teachingId)) {
            throw new RuntimeException("您已经对该课程进行过评价，无法重复提交");
        }

        // 强制覆盖 studentId，防止前端伪造其他人的ID提交（权限安全的第一步）
        for (EvaluationInfo info : evaluationList) {
            info.setStudentId(studentId);
        }

        // 执行批量插入
        evaluationMapper.insertBatch(evaluationList);
    }

    @Override
    public boolean hasEvaluated(Integer studentId, Integer teachingId) {
        return evaluationMapper.checkIsEvaluated(studentId, teachingId) > 0;
    }
}