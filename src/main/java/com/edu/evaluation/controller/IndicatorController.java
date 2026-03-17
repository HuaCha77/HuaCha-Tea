package com.edu.evaluation.controller;

import com.edu.evaluation.entity.IndicatorInfo;
import com.edu.evaluation.service.IndicatorService;
import com.edu.evaluation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/indicator")
public class IndicatorController {
    @Autowired
    private IndicatorService indicatorService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("indicatorList", indicatorService.getAll());
        return "indicator/list";
    }

    @PostMapping("/add")
    @ResponseBody
    public Result<String> add(@RequestBody IndicatorInfo indicator) {
        indicatorService.save(indicator);
        return Result.success("操作成功");
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Result<IndicatorInfo> get(@PathVariable Integer id) {
        return Result.success(indicatorService.getById(id));
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result<String> delete(@PathVariable Integer id) {
        indicatorService.delete(id);
        return Result.success("删除成功");
    }
}
