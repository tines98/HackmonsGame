package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IItem {
    String getName();
    void render(SpriteBatch batch, int x, int y, int w, int h);
    void useItem(Hackmon applyTo);
    String getDesc();
}
