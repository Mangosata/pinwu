package com.pinwu.auth;

import com.pinwu.auth.service.impl.RedisServiceImpl;
import com.pinwu.auth.service.impl.SendEmailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
@RunWith(SpringRunner.class)
class AuthApplicationTests {
    @Autowired
    RedisServiceImpl redisService;

    @Autowired
    SendEmailService sendEmailService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testRedisSet() {
      redisService.set("test", "value");
    }

    @Test
    public void testSend() {
        sendEmailService.sendMail("80226421@qq.com");
    }

    @Test
    public void testCheckCode() {
        Boolean qcmQ = sendEmailService.checkCode("80226421@qq.com", "qcmQ");
        System.out.println(qcmQ);
    }

}
