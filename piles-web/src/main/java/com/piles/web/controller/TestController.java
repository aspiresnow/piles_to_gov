package com.piles.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dict")
public class TestController {

    private String PREFIX = "/pages/modular/system/dict/";

    @RequestMapping("")
    public String index() {
//        return PREFIX + "dict";
        return "system/index";
    }
    @RequestMapping("/test")
    public String test() {
//        return PREFIX + "dict";
        return "test/aaa";
    }

    /**
     * 跳转到添加字典类型
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:21 PM
     */
    @RequestMapping("/dict_add_type")
    public String deptAddType() {
        return PREFIX + "dict_add_type.html";
    }

    /**
     * 跳转到添加字典条目
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:22 PM
     */
    @RequestMapping("/dict_add_item")
    public String deptAddItem(@RequestParam("dictId") Long dictId, Model model) {
        model.addAttribute("dictTypeId", dictId);
        return PREFIX + "dict_add_item.html";
    }

    /**
     * 获取所有字典列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:22 PM
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
//        return LayuiPageFactory.createPageInfo(warpper);
        return null;
    }

    /**
     * 删除字典记录
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:22 PM
     */
//    @RequestMapping(value = "/delete")
//    @ResponseBody
//    public ResponseData delete(@RequestParam Long dictId) {
//
//        //缓存被删除的名称
//        return SUCCESS_TIP;
//    }

}
