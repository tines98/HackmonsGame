package com.mygdx.game;

public class StatusDisplay {
    int x, y, w, h;
    HpBar hp;
    StaminaBar stam;
    Trainer trainer;

    public StatusDisplay(Trainer trainer, int x, int y){
        this.trainer = trainer;
        w = 150;
        h = 100;
        hp = new HpBar(x+25,y+h-20);
        hp.setTrainer(trainer);
        hp = new HpBar(x+25,y+h-20);
        hp.setTrainer(trainer);
    }
}
