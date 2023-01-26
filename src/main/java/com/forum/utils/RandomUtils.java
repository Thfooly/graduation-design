package com.forum.utils;

import java.util.Random;

public class RandomUtils {

    /**
     * 生成六位
     * @return
     */
    public static String getRandom(){
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        return result;
    }
}
