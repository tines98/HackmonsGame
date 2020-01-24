package com.mygdx.game.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Hackmon;

public interface IItem {
    String getName();
    void render(SpriteBatch batch, int x, int y, int w, int h);
    void useItem(Hackmon applyTo);
    String getDesc();
}
