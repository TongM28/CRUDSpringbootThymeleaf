package com.t2404e.jobboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Điều hướng về trang danh sách tin tuyển dụng làm trang chủ
        return "redirect:/jobs";
    }
}