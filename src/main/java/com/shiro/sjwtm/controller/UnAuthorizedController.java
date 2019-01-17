package com.shiro.sjwtm.controller;

import com.shiro.sjwtm.common.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("未认证跳转页面")
public class UnAuthorizedController {

    @GetMapping("unauthorized/{message}")
    public Result error(@PathVariable String message) {
        return Result.error(message);
    }

}
