package com.tingyun.controller;

import com.tingyun.annocation.Apple;
import com.tingyun.service.AppleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private AppleService appleService;

    @RequestMapping("/test")
    public String apple() {
        appleService.getClass().getAnnotation(Apple.class);
         appleService.appleDomain().toString();
         return "page/test";
    }
}
