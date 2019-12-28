package com.mygdx.game;

import java.util.Random;

public class Attack {

    //TODO Move to static "chance" class
    Random random = new Random();

    public int damage(Hackmon attacker, Hackmon defender, Move move) {
        if (move.getCategory().equals("Physical")) {
            return attacker.getLv() * move.getPower() * (attacker.getStr() / defender.getDef());
        }
        else {
            return attacker.getLv() * move.getPower() * (attacker.getWill() / defender.getRes());
        }
    }

    public double modify(Hackmon attacker, Hackmon defender, Move move) {

        int critical = (attacker.getCrit() > random.nextInt(100)) ? 2 : 1;
        double variance = (100 - random.nextInt(16)) / 100;
        return critical * variance;
    }

    public String attackStandard(Hackmon attacker, Hackmon defender, Move move) {
        if (move.getAccuarcy() > random.nextInt(100)) {
            int damage = (int) (damage(attacker, defender, move) * modify(attacker, defender, move));
            if (damage == 0) {
                return defender.getName() + " is immune...";
            }
            defender.takeDamage(damage);
        }
        return "The attack missed!";
    }
}
