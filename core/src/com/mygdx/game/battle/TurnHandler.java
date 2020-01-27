package com.mygdx.game.battle;

import com.mygdx.game.Move;

public class TurnHandler {
    private static boolean ready = false;
    private static int cooldown = 0;
    private static int action = 2;
    private static Move currentMove = null;
    private static String currentMessage = "crigogini";

    public static boolean isReady() {
        return ready;
    }

    public static void setReady () {
        ready = true;
    }

    public static void unReady() {
        ready = false;
    }

    public static int getAction() {
        return action;
    }

    public static void setAction(int newAction) {
        action = newAction;
    }

    public static Move getCurrentMove() {
        return currentMove;
    }

    public static void setCurrentMove(Move newMove) {
        currentMove = newMove;
    }

    public static String getCurrentMessage() {
        return currentMessage;
    }

    public static void setCurrentMessage(String newMessage) {
        currentMessage = newMessage;
    }
}
