package com.edu.evaluation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class StatsViewController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "stats/dashboard";
    }
}
