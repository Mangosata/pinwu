package com.pinwu.auth.controller;

import com.pinwu.auth.constant.JsonResult;
import com.pinwu.auth.entity.User;
import com.pinwu.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bin Jia
 * @date 2020/9/6 0:48
 */
@RestController
@RequestMapping("/auth")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * 注册方法,用户输入注册用户名和密码,查询数据库是否已经存在该用户名,
   * 如果有,返回用户名已经注册.如果没有,返回注册成功.
   * @param user 用户名和密码
   * @return 信息
   */
  @PostMapping("/register")
  public JsonResult userRegister(@RequestBody User user) {
    return userService.userRegister(user);
  }

  @GetMapping("/login")
  public JsonResult userLogin(@RequestBody User user) {
    return userService.userLogin(user);
  }


}
