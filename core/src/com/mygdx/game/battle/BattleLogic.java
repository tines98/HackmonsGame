package com.mygdx.game.battle;

import com.mygdx.game.*;

public class BattleLogic {
    private static Trainer player, opponent;
    private static int playerSleepTimer = 0;
    private static int opponentSleepTimer = 0;

    public static void turn(BattleAction playerAction, BattleAction opponentAction) {
        BattleInfoBox.flush();
        switch(playerAction) {
            case ATTACK:
                switch(opponentAction) {
                    case ATTACK:
                        if (player_First()) {
                            if (statusCheckBeforeAction(player.getSelected())) {
                                turnAttack(player, opponent, true);
                            }
                            if (!checkFainted(opponent.getSelected(), false)) {
                                if (statusCheckBeforeAction(opponent.getSelected())) {
                                    turnAttack(opponent, player, false);
                                }
                                checkFainted(player.getSelected(), true);
                            }
                        }
                        else {
                            if (statusCheckBeforeAction(opponent.getSelected())) {
                                turnAttack(opponent, player, false);
                            }
                            if (!checkFainted(player.getSelected(), false)) {
                                if (statusCheckBeforeAction(player.getSelected())) {
                                    turnAttack(player, opponent, true);
                                }
                                checkFainted(opponent.getSelected(), false);
                            }
                        }
                        break;
                    case SWITCH:
                        for (int i=0; i < 5; i++) {
                            opponent.getSelected().resetModifiers();
                        }
                    default:
                        if (statusCheckBeforeAction(player.getSelected())) {
                            turnAttack(player, opponent, true);
                        }
                }
                break;
            case SWITCH:
                player.getSelected().resetModifiers();
                break;
            default:
                if (statusCheckBeforeAction(opponent.getSelected())) {
                    turnAttack(opponent, player, false);
                }
                break;
        }
        statusCheckAfterAction(player.getSelected());
        statusCheckAfterAction(opponent.getSelected());
        checkFainted(opponent.getSelected(), false);
        checkFainted(player.getSelected(), true);
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
            if (player.getSelected().getSpeed() * playerParalyzed *
                    player.getSelected().getModifier(4).getDecimal() >
                    opponent.getSelected().getSpeed() * opponentParalyzed *
                    opponent.getSelected().getModifier(4).getDecimal()) {
                return true;
            }
            else if (player.getSelected().getSpeed() * playerParalyzed *
                    player.getSelected().getModifier(4).getDecimal() <
                    opponent.getSelected().getSpeed() * opponentParalyzed *
                    opponent.getSelected().getModifier(4).getDecimal()) {
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

    public static void turnAttack(Trainer attacker, Trainer defender, boolean isPlayer) {
        if (isPlayer) {
            Attack.attack(attacker.getSelected(), defender.getSelected(), TurnHandler.getCurrentMove());
        }
        else {
            Attack.attack(attacker.getSelected(), defender.getSelected(), Opponent.doTurn());
        }
    }

    public static boolean checkFainted(Hackmon mon, boolean isPlayer) {
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