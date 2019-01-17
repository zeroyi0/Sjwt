package com.shiro.sjwtm.controller;

import com.shiro.sjwtm.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("信息测试")
public class InfoController {

    @GetMapping("info")
    @RequiresPermissions("admin:read")
    public Result info() {
        return Result.ok("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
    }

}
