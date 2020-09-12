package com.pinwu.auth.controller;

import com.pinwu.auth.constant.JsonResult;
import com.pinwu.auth.entity.User;
import com.pinwu.auth.service.UserService;
import com.pinwu.auth.service.impl.SendEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bin Jia
 * @date 2020/9/6 0:48
 */
@RestController
@Api(tags = "用户登录及注册")
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SendEmailService sendEmailServicel;

    /**
     * 注册方法
     *
     * @param user 用户名和密码
     * @return 信息
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public JsonResult userRegister(@RequestBody User user) {
        return userService.userRegister(user);
    }


    /**
     * 登录方法
     *
     * @param user 用户信息
     * @return 信息
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public JsonResult userLogin(@RequestBody User user) {
        return userService.userLogin(user);
    }


    /**
     * 发送验证码
     *
     * @param email 用户邮箱
     * @return 信息
     */
    @GetMapping("/send")
    public JsonResult send(@RequestParam String email) {
        JsonResult<String> result = new JsonResult<>();
        try {
            sendEmailServicel.sendMail(email);
            result.setMsg("发送验证码成功");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg("发送验证码失败");
            result.setSuccess(false);
        }
        return result;
    }


}
