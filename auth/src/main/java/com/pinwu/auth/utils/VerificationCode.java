package com.pinwu.auth.utils;

import java.util.Random;

/**
 * 验证码相关操作
 *
 * @author sata
 * @date 2020/9/12
 */
public class VerificationCode {
    /**
     * 随机生成四位验证码
     *
     * @return 验证码
     */
    public String generate() {
        Random random = new Random();
        char[] chars = new char[4];

        for (int i = 0; i < 4; i++) {
            Character c = null;
            switch (random.nextInt(3)) {
                case 0:
                    c = (char) ('A' + random.nextInt('Z' - 'A' + 1));
                    break;
                case 1:
                    c = (char) ('0' + random.nextInt('9' - '0' + 1));
                    break;
                case 2:
                    c = (char) ('a' + random.nextInt('z' - 'a' + 1));
                    break;
            }
            chars[i] = c;
        }
        return String.valueOf(chars);
    }
}
