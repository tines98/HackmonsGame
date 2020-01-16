package com.mygdx.game;

public class StaminaBar extends AbstractResourceBar {

    public StaminaBar(int x, int y) {
        super(x, y);
    }

    @Override
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
        prevResourceAmount = trainer.getSelected().getCurrStam();
    }


    @Override
    public void checkChange() {
        if (prevResourceAmount != trainer.getSelected().getCurrStam()){
            currSize =
                    maxSize * ((float)trainer.getSelected().getCurrStam()/(float)trainer.getSelected().getStam());
        }
    }

    @Override
    public String getText() {
        return "STAMINA: "+trainer.getSelected().getCurrStam()+"/"+trainer.getSelected().getStam();
    }
}
