package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BattleMenu {

    private int x,y,width,height;
    private static final Texture gray =
        new Texture("core/assets/gray.png");

    public BattleMenu(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }

    public void render(SpriteBatch batch){
        batch.draw(gray,x,y,width,height);
    }
}
