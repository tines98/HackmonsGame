package com.mygdx.game;

public class Attack {

    public static int damage(Hackmon attacker, Hackmon defender, Move move) {
        if (move.getCategory().equals("Physical")) {
            return attacker.getLv() * move.getPower() * (attacker.getStr() / defender.getDef());
        }
        else {
            return attacker.getLv() * move.getPower() * (attacker.getWill() / defender.getRes());
        }
    }

    public static double modify(Hackmon attacker, Hackmon defender, Move move) {

        int critical = RNG.chance(attacker.getCrit()) ? 2 : 1;
        double variance = (100 - RNG.nextInt(15)) / 100;
        return critical * variance;
    }

    public static void attack(Hackmon attacker, Hackmon defender, Move move) {
        int function = Integer.parseInt(move.getFunctionCode(), 16);
        switch (function) {
            case 0:
                attackStandard(attacker, defender, move);
            default:
                attackStandard(attacker, defender, move);
        }
    }

    public static String attackStandard(Hackmon attacker, Hackmon defender, Move move) {
        if (RNG.chance(move.getAccuarcy())) {
            int damage = (int) (damage(attacker, defender, move) * modify(attacker, defender, move));
            if (damage == 0) {
                return defender.getName() + " is immune...";
            }
            defender.takeDamage(damage);
        }
        return "The attack missed!";
    }
}
