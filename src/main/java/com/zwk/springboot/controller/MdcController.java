package com.zwk.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-02 11:42
 */
@Controller
@RequestMapping("mdc")
public class MdcController {
    @RequestMapping("tel")
    public String toTel(){
        return "/mdc/tel";
    }
}
