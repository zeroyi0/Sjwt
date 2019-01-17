package com.shiro.sjwtm.controller;

import com.shiro.sjwtm.common.utils.JWTUtils;
import com.shiro.sjwtm.common.utils.Result;
import com.shiro.sjwtm.controller.vo.UserVo;
import com.shiro.sjwtm.dao.entity.User;
import com.shiro.sjwtm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
@Api("用户登录")
public class LoginController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("login")
    @ApiOperation("登录接口")
    public Result login(@RequestBody UserVo userVo) {
        if (StringUtils.isEmpty(userVo.getUserName()) || StringUtils.isEmpty(userVo.getUserPwd())) {
            return Result.error("用户名和密码不能为空！");
        }

        User user = userService.getUserByUserNameAndPassWord(userVo);
        if (user == null) {
            return Result.error("账户不存在!");
        }

        /*Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(userVo.getUserName(), userVo.getUserPwd()));
        } catch (UnknownAccountException e) {
            return Result.error("账户不存在!");
        } catch (IncorrectCredentialsException e) {
            return Result.error("密码错误！");
        } catch (AuthenticationException e) {
            return Result.error("服务器繁忙，登录失败！");
        }*/

        Map res = new HashMap();
        res.put("token", JWTUtils.generateToken(userVo.getUserName()));
        return Result.ok(res);
    }

    @GetMapping("login")
    @ApiOperation("登录页面")
    public String login() {
        return "login";
    }

    @GetMapping("index")
    @ApiOperation("主页面")
    public String index() {
        return "index";
    }

}
