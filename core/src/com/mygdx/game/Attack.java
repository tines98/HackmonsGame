package com.mygdx.game;

import java.util.Random;

public class Attack {

    //TODO Move to static "chance" class
    Random random = new Random();

    public boolean attackStandard(Hackmon attacker, Hackmon defender, Move move) {
        if (move.getAccuarcy() > random.nextInt(100)) {
            if (move.getCategory().equals("Physical")) {
                int damage = (attacker.getStr() * attacker.getLv()) / (defender.getDef() * defender.getLv());
                defender.takeDamage(damage);
                return true;
            }
            else {
                int damage = (attacker.getWill() * attacker.getLv()) / (defender.getRes() * defender.getLv());
                defender.takeDamage(damage);
                return true;
            }
        }
        return false;
    }
}
