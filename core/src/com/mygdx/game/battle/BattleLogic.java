package com.mygdx.game.battle;

import com.mygdx.game.*;

public class BattleLogic {
    static Trainer player, opponent;
    static int playerSleepTimer = 0;
    static int opponentSleepTimer = 0;

    public static void turn(BattleAction playerAction, BattleAction opponentAction) {
        switch(playerAction) {
            case ATTACK:
                switch(opponentAction) {
                    case ATTACK:
                        //STATUS CHECK
                        if (player_First()) { ;
                            turn_attack(player, opponent, true);
                            if (!check_fainted(opponent.getSelected(), false)) {
                                turn_attack(opponent, player, false);
                                check_fainted(player.getSelected(), true);
                            }
                        }
                        else {
                            turn_attack(opponent, player, false);
                            if (!check_fainted(player.getSelected(), false)) {
                                turn_attack(player, player, true);
                                check_fainted(opponent.getSelected(), false);
                            }
                        }
                        break;
                    default:
                        turn_attack(player, opponent, true);
                }
                break;
            default:
                turn_attack(opponent, player, false);
                break;
        }
        statusCheckAfterAction(player.getSelected());
        statusCheckAfterAction(opponent.getSelected());
        check_fainted(opponent.getSelected(), false);
        check_fainted(player.getSelected(), true);
    }

    public static boolean player_First() {
        if (TurnHandler.getCurrentMove().getPriority() > Opponent.doTurn().getPriority()) {
            return true;
        }
        else if (TurnHandler.getCurrentMove().getPriority() < Opponent.doTurn().getPriority()) {
            return false;
        }
        else {
            if (player.getSelected().getSpeed() > opponent.getSelected().getSpeed()) {
                return true;
            }
            else if (player.getSelected().getSpeed() < opponent.getSelected().getSpeed()){
                return false;
            }
            else {
                if (RNG.chance(50)) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }

    public static void turn_attack(Trainer attacker, Trainer defender, boolean isPlayer) {
        if (isPlayer) {
            Attack.attack(attacker.getSelected(), defender.getSelected(), TurnHandler.getCurrentMove());
        }
        else {
            Attack.attack(attacker.getSelected(), defender.getSelected(), Opponent.doTurn());
        }
    }

    public static boolean check_fainted(Hackmon mon, boolean isPlayer) {
        if (mon.isFainted()) {
            if (isPlayer) {
                HackmonsGame.changeScreenState(ScreenState.SWITCHMENU);
            }
            else {
                opponent.switchMon(opponent.nextMon());
                opponent.getSelected().setToFront();
            }
            return true;
        }
        return false;
    }

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
        statusCheckAfterAction(player.getSelected());
        statusCheckAfterAction(opponent.getSelected());
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
        statusCheckAfterAction(player.getSelected());
        statusCheckAfterAction(opponent.getSelected());
    }

    public static void statusCheckBeforeAction(Trainer trainer, Hackmon mon) {
        switch(mon.getStatus()) {
            case PARALYZED:
                if (RNG.chance(50)) {
                    turnAttack();
                }
                else {
                    turnPlayer();
                }
                break;
            case SLEEP:
                if (RNG.chance(50)) {
                    mon.setStatus(StatusEffect.NONE);
                    turnAttack();
                }
                else {
                    turnPlayer();
                }
            default:
                break;
        }
    }

    public static void statusCheckAfterAction(Hackmon mon) {
        switch(mon.getStatus()) {
            case BURN:
                mon.takeDamage(mon.getHp() / 16);
                break;
            case POISON:
                mon.takeDamage(mon.getHp() / 8);
                break;
            default:
                break;
        }
    }

    public static void turnPlayer() {
        Attack.attack(player.getSelected(), opponent.getSelected(), TurnHandler.getCurrentMove());
        opponent.getSelected().restoreStam(player.getSelected().getStam() / 8);
        statusCheckAfterAction(player.getSelected());
        statusCheckAfterAction(opponent.getSelected());
        if (opponent.getSelected().isFainted()) {
            opponent.switchMon(opponent.nextMon());
            opponent.getSelected().setToFront();
        }
    }

    public static void turnPass() {
        Attack.attack(opponent.getSelected(), player.getSelected(), Opponent.doTurn());
        player.getSelected().restoreStam(player.getSelected().getStam() / 8);
        statusCheckAfterAction(player.getSelected());
        statusCheckAfterAction(opponent.getSelected());
        if (opponent.getSelected().isFainted()) {
            opponent.switchMon(opponent.nextMon());
            opponent.getSelected().setToFront();
        }
    }

    public static void setPlayer(Trainer newPlayer) {
        player = newPlayer;
    }

    public static void setOpponent(Trainer newOpponent) {
        opponent = newOpponent;
    }
}
