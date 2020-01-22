package com.mygdx.game.battle;

import com.mygdx.game.Hackmon;
import com.mygdx.game.Move;
import com.mygdx.game.RNG;

public class Attack {

    public static int damage(Hackmon attacker, Hackmon defender, Move move) {
        if (move.getCategory().equals("Physical")) {
            System.out.println(attacker.getStr() + " " + defender.getDef());
            return (attacker.getLv() / 4) * move.getPower() * (attacker.getStr() / defender.getDef()) + 2;
        }
        else {
            return (attacker.getLv() / 4 )* move.getPower() * (attacker.getWill() / defender.getRes()) + 2;
        }
    }

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
        int function = Integer.parseInt(move.getFunctionCode(), 16);
        switch (function) {
            case 0:
                attackStandard(attacker, defender, move);
                break;
            default:
                attackStandard(attacker, defender, move);
                break;
        }
    }

    public static String attackStandard(Hackmon attacker, Hackmon defender, Move move) {
        attacker.useStam(move.getCost());
        if (RNG.chance(move.getAccuarcy())) {
            int damage = (int) ((damage(attacker, defender, move) / 50) * modify(attacker, defender, move));
            System.out.println("Damage: " + damage);
            if (damage == 0) {
                return defender.getName() + " is immune...";
            }
            defender.takeDamage(damage);
        }
        return "The attack missed!";
    }
}
