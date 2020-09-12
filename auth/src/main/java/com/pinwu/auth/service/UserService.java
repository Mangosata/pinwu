package com.pinwu.auth.service;

import com.pinwu.auth.constant.JsonResult;
import com.pinwu.auth.constant.MessageConstant;
import com.pinwu.auth.entity.User;
import com.pinwu.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.SoundbankResource;

/**
 * @author Bin Jia
 * @date 2020/9/6 0:52
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisService redisService;

    /**
     * 注册用户
     * 使用用户名进行判断是否已经存在, 同时判断用户输入验证码是否正确
     *
     * @param user 用户实体类
     * @return 信息
     */
    public JsonResult userRegister(User user) {
        JsonResult<String> result = new JsonResult<>();
        result.setSuccess(false);
        result.setDetail(null);

        User existedUser = userRepository.getUserByUsername(user.getUsername());
        String verificationCode = redisService.get(user.getEmail());
        System.out.println(verificationCode);
        System.out.println(user.getVerificationCode());

        if (existedUser == null && verificationCode.equals(user.getVerificationCode())) {
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

        existedUser = existedUser == null ? userRepository.getUserByEmail(user.getEmail()) : existedUser;

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
