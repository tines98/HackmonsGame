package com.mygdx.game.battle;

import com.mygdx.game.Hackmon;
import com.mygdx.game.Move;
import com.mygdx.game.RNG;
import com.mygdx.game.StatusEffect;

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
        //printShit(attacker, defender, move);
        if (move.getCategory().equals("Physical")) {
            System.out.println(attacker.getStr() + " " + defender.getDef());
            double damage = (attacker.getLv() / 4) * move.getPower() *
                    ((attacker.getStr() * attacker.getModifier(0).getDecimal()) /
                            (defender.getDef() * defender.getModifier(1).getDecimal())) / 50;
            if (attacker.getStatus() == StatusEffect.BURN) {
                damage /= 2;
            }
            return damage;
        }
        if (move.getCategory().equals("Special")) {
            double damage = (attacker.getLv() / 4 )* move.getPower() *
                    ((attacker.getWill() *  attacker.getModifier(2).getDecimal()
                            / (defender.getRes() * defender.getModifier(3).getDecimal()))) / 50;
            if (attacker.getStatus() == StatusEffect.FEAR) {
                damage /= 2;
            }
            return damage;
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
            case 512:
                attackBurn(attacker, defender, move);
                break;
            case 528:
                attackFear(attacker, defender, move);
                break;
            case 544:
                attackFreeze(attacker, defender, move);
                break;
            case 560:
                attackParalyze(attacker, defender, move);
                break;
            case 576:
                attackPoison(attacker, defender, move);
                break;
            case 592:
                attackSleep(attacker, defender, move);
                break;
            case 768:
                increaseStrength(attacker, 1);
                break;
            case 769:
                increaseStrength(attacker, 2);
                break;
            case 770:
                increaseStrength(attacker, 3);
                break;
            case 771:
                decreaseStrength(attacker, 1);
                break;
            case 772:
                decreaseStrength(attacker, 2);
                break;
            case 773:
                decreaseStrength(attacker, 3);
                break;
            case 774:
                increaseStrength(defender, 1);
                break;
            case 775:
                increaseStrength(defender, 2);
                break;
            case 776:
                increaseStrength(defender, 3);
                break;
            case 777:
                decreaseStrength(defender, 1);
                break;
            case 778:
                decreaseStrength(defender, 2);
                break;
            case 779:
                decreaseStrength(defender, 3);
                break;
            case 784:
                increaseDefense(attacker, 1);
                break;
            case 785:
                increaseDefense(attacker, 2);
                break;
            case 786:
                increaseDefense(attacker, 3);
                break;
            case 787:
                decreaseDefense(attacker, 1);
                break;
            case 788:
                decreaseDefense(attacker, 2);
                break;
            case 789:
                decreaseDefense(attacker, 3);
                break;
            case 790:
                increaseDefense(defender, 1);
                break;
            case 791:
                increaseDefense(defender, 2);
                break;
            case 792:
                increaseDefense(defender, 3);
                break;
            case 793:
                decreaseDefense(defender, 1);
                break;
            case 794:
                decreaseDefense(defender, 2);
                break;
            case 795:
                decreaseDefense(defender, 3);
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
        else {
            BattleInfoBox.addToText("The attack missed!");
        }
    }

    public static void attackBurn(Hackmon attacker, Hackmon defender, Move move) {
        if (RNG.chance(move.getAccuarcy())) {
            int damage = (int) ((damage(attacker, defender, move) + 2) * modify(attacker, defender, move));
            defender.takeDamage(damage);
            if (RNG.chance(move.getEffectAccuarcy())) {
                BattleInfoBox.addToText(defender.getName() + " was burned!");
                defender.setStatus(StatusEffect.BURN);
            }
        }
    }

    public static void attackFear(Hackmon attacker, Hackmon defender, Move move) {
        if (RNG.chance(move.getAccuarcy())) {
            int damage = (int) ((damage(attacker, defender, move) + 2) * modify(attacker, defender, move));
            defender.takeDamage(damage);
            if (RNG.chance(move.getEffectAccuarcy())) {
                BattleInfoBox.addToText(defender.getName() + " was scared!");
                defender.setStatus(StatusEffect.FEAR);
            }
        }
    }

    public static void attackFreeze(Hackmon attacker, Hackmon defender, Move move) {
        if (RNG.chance(move.getAccuarcy())) {
            int damage = (int) ((damage(attacker, defender, move) + 2) * modify(attacker, defender, move));
            defender.takeDamage(damage);
            if (RNG.chance(move.getEffectAccuarcy())) {
                BattleInfoBox.addToText(defender.getName() + " was frozen!");
                defender.setStatus(StatusEffect.FROZEN);
            }
        }
    }

    public static void attackParalyze(Hackmon attacker, Hackmon defender, Move move) {
        if (RNG.chance(move.getAccuarcy())) {
            int damage = (int) ((damage(attacker, defender, move) + 2) * modify(attacker, defender, move));
            defender.takeDamage(damage);
            if (RNG.chance(move.getEffectAccuarcy())) {
                BattleInfoBox.addToText(defender.getName() + " was paralyzed!");
                defender.setStatus(StatusEffect.PARALYZED);
            }
        }
    }

    public static void attackPoison(Hackmon attacker, Hackmon defender, Move move) {
        if (RNG.chance(move.getAccuarcy())) {
            int damage = (int) ((damage(attacker, defender, move) + 2) * modify(attacker, defender, move));
            defender.takeDamage(damage);
            if (RNG.chance(move.getEffectAccuarcy())) {
                BattleInfoBox.addToText(defender.getName() + " was poisoned!");
                defender.setStatus(StatusEffect.POISON);
            }
        }
    }

    public static void attackSleep(Hackmon attacker, Hackmon defender, Move move) {
        if (RNG.chance(move.getAccuarcy())) {
            int damage = (int) ((damage(attacker, defender, move) + 2) * modify(attacker, defender, move));
            defender.takeDamage(damage);
            if (RNG.chance(move.getEffectAccuarcy())) {
                BattleInfoBox.addToText(defender.getName() + " fell asleep!");
                defender.setStatus(StatusEffect.SLEEP);
            }
        }
    }

    public static void increaseStrength(Hackmon mon, int stages) {

        BattleInfoBox.addToText(mon.getName() + " had its Strength increased by " + stages + " stages!");
        mon.getModifier(0).increase(stages);
    }

    public static void decreaseStrength(Hackmon mon, int stages) {
        BattleInfoBox.addToText(mon.getName() + " had its Strength decreased by " + stages + " stages!");
        mon.getModifier(0).decrease(stages);
    }

    public static void increaseDefense(Hackmon mon, int stages) {
        BattleInfoBox.addToText(mon.getName() + " had its Defense increased by " + stages + " stages!");
        mon.getModifier(1).increase(stages);
    }

    public static void decreaseDefense(Hackmon mon, int stages) {
        BattleInfoBox.addToText(mon.getName() + " had its Defense decreased by " + stages + " stages!");
        mon.getModifier(1).decrease(stages);
    }

    public static void increaseWill(Hackmon mon, int stages) {
        BattleInfoBox.addToText(mon.getName() + " had its Will increased by " + stages + " stages!");
        mon.getModifier(2).increase(stages);
    }

    public static void decreaseWill(Hackmon mon, int stages) {
        BattleInfoBox.addToText(mon.getName() + " had its Will decreased by " + stages + " stages!");
        mon.getModifier(2).decrease(stages);
    }

    public static void increaseResistance(Hackmon mon, int stages) {
        BattleInfoBox.addToText(mon.getName() + " had its Resistance increased by " + stages + " stages!");
        mon.getModifier(3).increase(stages);
    }

    public static void decreaseResistance(Hackmon mon, int stages) {
        BattleInfoBox.addToText(mon.getName() + " had its Resistance decreased by " + stages + " stages!");
        mon.getModifier(3).decrease(stages);
    }

    public static void increaseSpeed(Hackmon mon, int stages) {
        BattleInfoBox.addToText(mon.getName() + " had its Speed increased by " + stages + " stages!");
        mon.getModifier(4).increase(stages);
    }

    public static void decreaseSpeed(Hackmon mon, int stages) {
        BattleInfoBox.addToText(mon.getName() + " had its Speed decreased by " + stages + " stages!");
        mon.getModifier(4).decrease(stages);
    }
}