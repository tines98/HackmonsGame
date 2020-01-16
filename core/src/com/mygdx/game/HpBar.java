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
    public void checkChange() {
        if (prevResourceAmount != trainer.getSelected().getCurrHp()){
            currSize =
                    maxSize * ((float)trainer.getSelected().getCurrHp()/(float)trainer.getSelected().getHp());
        }
    }

    @Override
    public String getText() {
        return "HP: "+trainer.getSelected().getCurrHp()+"/"+trainer.getSelected().getHp();
    }
}
