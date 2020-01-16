package com.mygdx.game;

import java.util.Random;

public class RNG {

    private static Random random = new Random();

    /**
     * Compares given percentage to a rondom number between
     * 0 inclusve and 100 exclusive.
     * @param prob percentage of success
     * @return True if success, false otherwise.
     */
    public static boolean chance(int prob){
        return (prob > random.nextInt(100));
    }

    /**
     * Returns a random int between 0 and given limit inclusive
     * @param limit Highest possible value
     * @return random int between 0-limit inclusive
     */
    public static int nextInt(int limit){
        return random.nextInt(limit + 1);
    }
}
