package com.mygdx.game.battle;

import com.mygdx.game.Hackmon;
import com.mygdx.game.Move;
import com.mygdx.game.RNG;
import com.mygdx.game.StatusEffect;
import jdk.net.SocketFlow;

/**
 * A class controlling all forms for attacks a move can do, and does all the calculations necessary
 */
public class Attack {

    private static void printShit(Hackmon attacker, Hackmon defender, Move move) {
        System.out.println("Attacker: " + attacker.getName() + " Defender: " + defender.getName());
        System.out.println("A Lv: " + attacker.getLv() + " D Lv: " + defender.getLv());
        System.out.println("A Stats: " + attacker.getStr() + " " + attacker.getDef() + " " +
                attacker.getWill() + " " + attacker.getRes() + " " + attacker.getSpeed());
        System.out.println("D Stats: " + defender.getStr() + " " + defender.getDef() + " " +
                defender.getWill() + " " + defender.getRes() + " " + defender.getSpeed());
    }

    public static double damage(Hackmon attacker, Hackmon defender, Move move) {
        printShit(attacker, defender, move);
        if (move.getCategory().equals("Physical")) {
            System.out.println(attacker.getStr() + " " + defender.getDef());
            return (attacker.getLv() / 4) * move.getPower() *
                    ((attacker.getStr() * attacker.getModifier(0).getDecimal()) /
                            (defender.getDef() * defender.getModifier(1).getDecimal())) / 50;
        }
        if (move.getCategory().equals("Special")) {
            return (attacker.getLv() / 4 )* move.getPower() *
                    ((attacker.getWill() *  attacker.getModifier(2).getDecimal()
                            / (defender.getRes() * defender.getModifier(3).getDecimal()))) / 50;
        }
        else {
            return 0;
        }
    }

    /**
     * Calculated all elements that can affect the base damage a move will do: STAB, critical hit,
     * type effectiveness and random variance.
     * @param attacker the one that performs the attacks
     * @param defender the one that is being attacked
     * @param move what move is being used
     * @return the modifier (between 0 and ~ 6)
     */
    public static double modify(Hackmon attacker, Hackmon defender, Move move) {
        double STAB = 1;
        if (attacker.getType1().equals(move.getType()) || (attacker.getType2() != null
                && attacker.getType2().equals(move.getType()))) {
            STAB = 1.5;
        }
        System.out.println("STAB: " + STAB);
        double critical = RNG.chance(attacker.getCrit()) ? 2 : 1;
        double variance = (100.0 - RNG.nextInt(15)) / 100;
        System.out.println(critical * variance);
        return STAB * critical * variance;
    }

    public static void attack(Hackmon attacker, Hackmon defender, Move move) {
        BattleInfoBox.addToText(attacker.getName() + " used " + move.getName());
        attacker.useStam(move.getCost());
        int function = Integer.parseInt(move.getFunctionCode(), 16);
        switch (function) {
            case 0:
                attackStandard(attacker, defender, move);
                break;
            case 1:
                attackMultiHit(attacker, defender, move);
                break;
            case 160:
                attackBurn(attacker, defender, move);
                break;
            case 176:
                attackParalyze(attacker, defender, move);
                break;
            case 192:
                attackSleep(attacker, defender, move);
                break;
            default:
                attackStandard(attacker, defender, move);
                break;
        }
    }

    public static void attackStandard(Hackmon attacker, Hackmon defender, Move move) {
        attacker.useStam(move.getCost());
        if (RNG.chance(move.getAccuarcy())) {
            int damage = (int) ((damage(attacker, defender, move) + 2) * modify(attacker, defender, move));
            System.out.println("Damage: " + damage);
            defender.takeDamage(damage);
        }
        else {
            BattleInfoBox.addToText("The attack missed!");
        }
    }

    public static void attackMultiHit(Hackmon attacker, Hackmon defender, Move move) {
        if (RNG.chance(move.getAccuarcy())) {
            int hits = RNG.nextInt(3) + 2;
            for (int i=0; i < hits; i++) {
                int damage = (int) ((damage(attacker, defender, move) + 2) * modify(attacker, defender, move));
                System.out.println("Damage: " + damage);
                defender.takeDamage(damage);
                if (defender.isFainted()) {
                    BattleInfoBox.addToText("It hit " + i + " times!");
                    System.out.println("It hit " + i + " times!");
                    return;
                }
            }
            BattleInfoBox.addToText("It hit " + hits + " times!");
            System.out.println("It hit " + hits + " times!");
        }
    }

    public static void attackBurn(Hackmon attacker, Hackmon defender, Move move) {
        if (RNG.chance(move.getAccuarcy())) {
            int damage = (int) ((damage(attacker, defender, move) + 2) * modify(attacker, defender, move));
            defender.takeDamage(damage);
            if (RNG.chance(move.getEffectAccuarcy())) {
                defender.setStatus(StatusEffect.BURN);
            }
        }
    }

    public static void attackParalyze(Hackmon attacker, Hackmon defender, Move move) {
        if (RNG.chance(move.getAccuarcy())) {
            int damage = (int) ((damage(attacker, defender, move) + 2) * modify(attacker, defender, move));
            defender.takeDamage(damage);
            if (RNG.chance(move.getEffectAccuarcy())) {
                defender.setStatus(StatusEffect.PARALYZED);
            }
        }
    }

    public static void attackSleep(Hackmon attacker, Hackmon defender, Move move) {
        if (RNG.chance(move.getAccuarcy())) {
            int damage = (int) ((damage(attacker, defender, move) + 2) * modify(attacker, defender, move));
            defender.takeDamage(damage);
            if (RNG.chance(move.getEffectAccuarcy())) {
                defender.setStatus(StatusEffect.SLEEP);
            }
        }
    }
}