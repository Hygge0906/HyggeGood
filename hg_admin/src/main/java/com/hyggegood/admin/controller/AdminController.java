package com.hyggegood.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin") // 这里指定基础路径为 /admin
public class AdminController {

    @GetMapping("/test") // 处理 /admin/test 请求
    public String test() {
        return "this is test";
    }
}