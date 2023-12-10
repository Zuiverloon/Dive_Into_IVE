package org.zjy.diveintoive.utils;


import java.util.Arrays;
import java.util.Random;

public class RandomUtil {

    public static String getRandomSuffix(int len) {
        Random random = new Random();
        char[] randomCharArr = new char[len];
        for (int i = 0; i < len; i++) {
            randomCharArr[i] = (char) ('a' + random.nextInt(26));
        }
        return new String(randomCharArr);
    }
}
