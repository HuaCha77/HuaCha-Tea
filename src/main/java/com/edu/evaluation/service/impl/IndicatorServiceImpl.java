package com.edu.evaluation.service.impl;

import com.edu.evaluation.dao.IndicatorMapper;
import com.edu.evaluation.entity.IndicatorInfo;
import com.edu.evaluation.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicatorServiceImpl implements IndicatorService {
    @Autowired
    private IndicatorMapper indicatorMapper;

    @Override
    public List<IndicatorInfo> getAll() { return indicatorMapper.findAll(); }

    @Override
    public IndicatorInfo getById(Integer id) { return indicatorMapper.findById(id); }

    @Override
    public void save(IndicatorInfo indicator) {
        if (indicator.getId() == null) indicatorMapper.insert(indicator);
        else indicatorMapper.update(indicator);
    }

    @Override
    public void delete(Integer id) { indicatorMapper.deleteById(id); }
}
