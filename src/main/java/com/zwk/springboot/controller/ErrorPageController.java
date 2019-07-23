package com.zwk.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springboot
 * @description: 报错页面
 * @author: wkzhang
 * @create: 2019-07-22 11:46
 */
@Controller
@RequestMapping("error")
public class ErrorPageController {
    @RequestMapping("500")
    public String error_500(){
        return "/error/500";
    }
    @RequestMapping("404")
    public String error_404(){
        return "/error/404";
    }
}
