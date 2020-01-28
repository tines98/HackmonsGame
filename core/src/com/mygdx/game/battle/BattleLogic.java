package com.mygdx.game.battle;

import com.mygdx.game.*;

public class BattleLogic {
    private static Trainer player, opponent;
    private static int playerSleepTimer = 0;
    private static int opponentSleepTimer = 0;

    private static int [] playerModifiers = {1, 1, 1, 1, 1};
    private static int [] opponentModifiers = {1, 1, 1, 1, 1};

    public static void turn(BattleAction playerAction, BattleAction opponentAction) {
        switch(playerAction) {
            case ATTACK:
                switch(opponentAction) {
                    case ATTACK:
                        if (player_First()) {
                            if (statusCheckBeforeAction(player.getSelected())) {
                                turn_attack(player, opponent, true);
                            }
                            if (!check_fainted(opponent.getSelected(), false)) {
                                if (statusCheckBeforeAction(opponent.getSelected())) {
                                    turn_attack(opponent, player, false);
                                }
                                check_fainted(player.getSelected(), true);
                            }
                        }
                        else {
                            if (statusCheckBeforeAction(opponent.getSelected())) {
                                turn_attack(opponent, player, false);
                            }
                            if (!check_fainted(player.getSelected(), false)) {
                                if (statusCheckBeforeAction(player.getSelected())) {
                                    turn_attack(player, opponent, true);
                                }
                                check_fainted(opponent.getSelected(), false);
                            }
                        }
                        break;
                    default:
                        if (statusCheckBeforeAction(player.getSelected())) {
                            turn_attack(player, opponent, true);
                        }
                }
                break;
            default:
                if (statusCheckBeforeAction(opponent.getSelected())) {
                    turn_attack(opponent, player, false);
                }
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
            double playerParalyzed = player.getSelected().getStatus() == StatusEffect.PARALYZED ? 0.5 : 1;
            double  opponentParalyzed = opponent.getSelected().getStatus() == StatusEffect.PARALYZED ? 0.5 : 1;
            System.out.println("P speed: " + player.getSelected().getSpeed() * playerParalyzed
            + " O speed: " + opponent.getSelected().getSpeed() * opponentParalyzed);
            if (player.getSelected().getSpeed() * playerParalyzed >
                    opponent.getSelected().getSpeed() * opponentParalyzed) {
                return true;
            }
            else if (player.getSelected().getSpeed() * playerParalyzed <
                    opponent.getSelected().getSpeed() * opponentParalyzed){
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

    public static boolean statusCheckBeforeAction(Hackmon mon) {
        switch(mon.getStatus()) {
            case FROZEN:
                if (RNG.chance(40)) {
                    mon.setStatus(StatusEffect.NONE);
                    return true;
                }
                return false;
            case PARALYZED:
                if (RNG.chance(50)) {
                    return true;
                }
                return false;
            case SLEEP:
                if (RNG.chance(45)) {
                    mon.setStatus(StatusEffect.NONE);
                    return true;
                }
                return false;
            default:
                return true;
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

    public static void setPlayer(Trainer newPlayer) {
        player = newPlayer;
    }

    public static void setOpponent(Trainer newOpponent) {
        opponent = newOpponent;
    }
}