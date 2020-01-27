package com.mygdx.game.battle;

import com.mygdx.game.HackmonsGame;
import com.mygdx.game.RNG;
import com.mygdx.game.ScreenState;
import com.mygdx.game.Trainer;

public class BattleLogic {

    static Trainer player, opponent;

    public static void turnAttack() {
        if (TurnHandler.getCurrentMove().getPriority() > Opponent.doTurn().getPriority()) {
            playerFirst();
        }
        else if (TurnHandler.getCurrentMove().getPriority() < Opponent.doTurn().getPriority()) {
            opponentFirst();
        }
        else {
            if (player.getSelected().getSpeed() > opponent.getSelected().getSpeed()) {
                playerFirst();
            }
            else if (player.getSelected().getSpeed() < opponent.getSelected().getSpeed()){
                opponentFirst();
            }
            else {
                if (RNG.chance(50)) {
                    playerFirst();
                }
                else {
                    opponentFirst();
                }
            }
        }
    }

    public static void playerFirst() {
        Attack.attack(player.getSelected(), opponent.getSelected(), TurnHandler.getCurrentMove());
        if (opponent.getSelected().isFainted()) {
            opponent.switchMon(opponent.nextMon());
            opponent.getSelected().setToFront();
        }
        Attack.attack(opponent.getSelected(), player.getSelected(), Opponent.doTurn());
        if (player.getSelected().isFainted()) {
            HackmonsGame.changeScreenState(ScreenState.SWITCHMENU);
        }
    }

    public static void opponentFirst() {
        Attack.attack(opponent.getSelected(), player.getSelected(), Opponent.doTurn());
        if (player.getSelected().isFainted()) {
            HackmonsGame.changeScreenState(ScreenState.SWITCHMENU);
        }
                Attack.attack(player.getSelected(), opponent.getSelected(), TurnHandler.getCurrentMove());
        if (opponent.getSelected().isFainted()) {
            opponent.switchMon(opponent.nextMon());
            opponent.getSelected().setToFront();
        }
    }

    public static void turnPass() {
        Attack.attack(opponent.getSelected(), player.getSelected(), Opponent.doTurn());
        player.getSelected().restoreStam(player.getSelected().getStam() / 8);
    }

    public static void setPlayer(Trainer newPlayer) {
        player = newPlayer;
    }

    public static void setOpponent(Trainer newOpponent) {
        opponent = newOpponent;
    }
}
