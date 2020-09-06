package com.pinwu.auth.service;

import com.pinwu.auth.constant.JsonResult;
import com.pinwu.auth.constant.MessageConstant;
import com.pinwu.auth.entity.User;
import com.pinwu.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bin Jia
 * @date 2020/9/6 0:52
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public JsonResult userRegister(User user) {
    JsonResult<String> result = new JsonResult<>();
    result.setSuccess(false);
    result.setDetail(null);

    User existedUser = userRepository.getUserByUsername(user.getUsername());

    if (existedUser == null) {
      userRepository.save(user);
      result.setSuccess(true);
      result.setDetail(user.getUsername());
      result.setMsg(MessageConstant.REGISTER_SUCCESS);
    } else {
      result.setMsg(MessageConstant.REGISTER_USERNAME_FAIL);
      return result;
    }
    return result;
  }

  public JsonResult<String> userLogin(User user) {
    JsonResult<String> result = new JsonResult<>();
    result.setSuccess(false);
    result.setDetail(null);

    User existedUser = userRepository.getUserByUsername(user.getUsername());

    if (existedUser == null) {
      result.setMsg(MessageConstant.LOGIN_USER_ERROR);
      return result;
    } else if (existedUser.getPassword().equals(user.getPassword())) {
      result.setMsg(MessageConstant.LOGIN_SUCCESS);
      result.setSuccess(true);
      result.setDetail(user.getUsername());
      return result;
    } else {
      result.setMsg(MessageConstant.USERNAME_PASSWORD_ERROR);
    }
    return result;

  }

}
