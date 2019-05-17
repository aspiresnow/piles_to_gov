package com.piles.web.controller;

import com.piles.sdk.entity.BaseQueryParam;
import com.piles.sdk.entity.CommonResponse;
import com.piles.sdk.entity.Station;
import com.piles.web.service.IStationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "用户相关的接口", tags = "station")
@Controller
@RequestMapping("/station")
public class StationController {

    @Autowired
    private IStationService stationService;
    private String PREFIX = "station/";

    @GetMapping({"", "/", "/list"})
    public String list() {
        //return LayuiPageFactory.createPageInfo(warpper);
        return PREFIX + "list";
    }

    @GetMapping({"queryForPage"})
    @ResponseBody
    public CommonResponse queryForPage(BaseQueryParam queryParam) {
        List<Station> stationList = stationService.getStationList();
        CommonResponse commonResponse = new CommonResponse(stationList);
        commonResponse.setCount(100);
        return commonResponse;
    }

    @GetMapping("/add")
    public String test() {
        return PREFIX + "add";
    }

    @GetMapping("/edit")
    public String edit() {
        return PREFIX + "edit";
    }

    @GetMapping("/detail")
    public String detail() {
        return PREFIX + "detail";
    }

    @ApiOperation(value = "添加", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "station", value = "用户信息", required = true, dataType = "Station"),
    })
    @PostMapping("/save")
    @ResponseBody
    public CommonResponse save(Station station) {
        return CommonResponse.success();
    }

    @ApiOperation(value = "删除", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int", paramType = "path"),
    })
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public CommonResponse delete(@PathVariable int id) {
        return CommonResponse.success();
    }
}
