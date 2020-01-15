package com.mygdx.game;

import java.util.Random;

public class RNG {

    private static Random random = new Random();

    public static boolean chance(int prob){
        return (prob > random.nextInt(100));
    }

    public static int nextInt(int limit){
        return random.nextInt(limit);
    }
}
