package com.piles.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: zhanglizhi
 * @Date: 2019/5/13 19:38
 * @Description:
 */
@Controller
public class SystemController {

    @RequestMapping({"","/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping({"/main"})
    public String main(){
        return "system/main";
    }
    @RequestMapping({"/welcome"})
    public String home(){
        return "system/welcome";
    }
}
