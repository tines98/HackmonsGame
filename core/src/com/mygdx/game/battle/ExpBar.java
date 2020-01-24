package com.mygdx.game.battle;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.AbstractResourceBar;
import com.mygdx.game.Colors;
import com.mygdx.game.Hackmon;
import com.mygdx.game.Trainer;

public class ExpBar extends AbstractResourceBar {
    Trainer trainer;

    public ExpBar(int x, int y) {
        super(x, y);
        height = 5;
    }

    @Override
    protected Texture getForeground() {
        return Colors.violet;
    }

    @Override
    protected Texture getBackground() {
        return Colors.black;
    }

    @Override
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public void checkChange(Hackmon hackmon) {
        currSize = maxSize *
                ((hackmon.getExp() - (float) Math.pow(hackmon.getLv(), 3)) /
                        (float) (Math.pow(hackmon.getLv() + 1, 3) - Math.pow(hackmon.getLv(), 3)));
    }

    @Override
    public String getText(Hackmon hackmon) {
        return "";
    }
}
