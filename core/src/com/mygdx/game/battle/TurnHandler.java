package com.mygdx.game.battle;

import com.mygdx.game.Move;

public class TurnHandler {
    private static boolean ready = false;
    private static int cooldown = 0;
    private static BattleAction action;
    private static Move currentMove = null;

    public static boolean isReady() {
        return ready;
    }

    public static void setReady () {
        ready = true;
    }

    public static void unReady() {
        ready = false;
    }

    public static BattleAction getAction() {
        return action;
    }

    public static void setAction(BattleAction newAction) {
        action = newAction;
    }

    public static Move getCurrentMove() {
        return currentMove;
    }

    public static void setCurrentMove(Move newMove) {
        currentMove = newMove;
    }
}
