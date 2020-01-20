package com.mygdx.game.battle;

import com.mygdx.game.Move;
import com.mygdx.game.Trainer;

public class Opponent {
    public static Trainer trainer;
    public static int difficulty;

    public static Move doTurn(){
        return trainer.getSelected().getMoves()[0];
    }

    public static void setTrainer(Trainer trainer) {
        Opponent.trainer = trainer;
    }

    public static void setDifficulty(int difficulty) {
        Opponent.difficulty = difficulty;
    }
}
