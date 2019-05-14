/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.piles.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 字典控制器
 *
 * @author fengshuonan
 * @Date 2017年4月26日 12:55:31
 */
@Controller
@RequestMapping("/dict")
public class TestController {

    private String PREFIX = "/pages/modular/system/dict/";

    /**
     * 跳转到字典管理首页
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:21 PM
     */
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
