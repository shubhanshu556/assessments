package com.marktine.assessment.utils;

public class RandomUtils {

    public static int generate(int max , int min) {

        return  (int)(Math.random()*(max-min+1)+min);

    }
}
