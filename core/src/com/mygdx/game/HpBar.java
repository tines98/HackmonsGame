package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class HpBar extends AbstractResourceBar {
    public HpBar(int x, int y) {
        super(x, y);
    }

    @Override
    protected Texture getForeground() {
        return Colors.green;
    }

    @Override
    protected Texture getBackground() {
        return Colors.red;
    }

    @Override
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
        prevResourceAmount = trainer.getSelected().getCurrHp();
    }


    @Override
    public void checkChange(Hackmon hackmon) {
//        if (prevResourceAmount != hackmon.getCurrHp()){
            currSize =
                maxSize * ((float)hackmon.getCurrHp()/(float)hackmon.getHp());
//        }
    }

    @Override
    public String getText(Hackmon hackmon) {
        return "HP: "+hackmon.getCurrHp()+"/"+hackmon.getHp();
    }
}
