package com.mygdx.game;

public class HpBar extends AbstractResourceBar {
    public HpBar(int x, int y) {
        super(x, y);
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
