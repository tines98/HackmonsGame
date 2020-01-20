package com.mygdx.game.battle;

public class TurnHandler {
    private static boolean ready = false;

    public static boolean isReady() {
        return ready;
    }

    public static void setReady () {
        ready = true;
    }

    public static void unReady() {
        ready = false;
    }
}
