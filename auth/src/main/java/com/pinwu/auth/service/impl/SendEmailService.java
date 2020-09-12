package com.pinwu.auth.service.impl;

import com.pinwu.auth.utils.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.sound.midi.SoundbankResource;

/**
 * 发送邮件服务
 *
 * @author sata
 * @date 2020/9/12
 */
@EnableAsync
@Service
public class SendEmailService {
    @Autowired
    RedisServiceImpl redisService;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.nickname}")
    private String nickname;

    @Value("${spring.mail.from}")
    private String from;

    /**
     * 异步请求
     * 发送验证码到用户邮箱
     *
     * @param emailAddress 用户邮箱
     */
    @Async
    public void sendMail(String emailAddress) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(nickname + "<" + from + ">");
        simpleMailMessage.setSubject("品物注册");
        simpleMailMessage.setTo(emailAddress);

        VerificationCode verificationCode = new VerificationCode();
        // 获取验证码
        String code = verificationCode.generate();

        simpleMailMessage.setText("您的验证码为: " + code);
        javaMailSender.send(simpleMailMessage);

        /**
         * 设置缓存, 并将过期时间设为300秒
         */
        redisService.set(emailAddress, code);
        redisService.expire(emailAddress, 300);
    }

    /**
     * 检查验证码是否正确
     *
     * @param email 用户邮箱
     * @param code  用户输入验证码
     * @return true 验证成功 false 验证失败
     */
    public Boolean checkCode(String email, String code) {
        String verificationCode = redisService.get(email);

        if (verificationCode != null && verificationCode.equals(code)) {
            redisService.remove(email);
            return true;
        }

        return false;

    }


}
