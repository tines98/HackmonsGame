package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class StaminaBar extends AbstractResourceBar {

    public StaminaBar(int x, int y) {
        super(x, y);
    }

    @Override
    protected Texture getForeground() {
        return Colors.blue;
    }

    @Override
    protected Texture getBackground() {
        return Colors.yellow;
    }

    @Override
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
        prevResourceAmount = trainer.getSelected().getCurrStam();
    }


    @Override
    public void checkChange(Hackmon hackmon) {
//        if (prevResourceAmount != hackmon.getCurrStam()){
            currSize =
                    maxSize * ((float)hackmon.getCurrStam()/(float)hackmon.getStam());
//        }
    }

    @Override
    public String getText(Hackmon hackmon) {
        return "SP: "+hackmon.getCurrStam()+"/"+hackmon.getStam();
    }
}
