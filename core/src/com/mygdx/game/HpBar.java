package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HpBar {
    private Hackmon hackmon;
    private int y, x;
    private static final Texture green =
        new Texture("core/assets/hpGreen.png");
    private static final Texture red =
        new Texture("core/assets/hpRed.png");
    private static final Texture black =
        new Texture("core/assets/black.png");

    public HpBar(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setHackmon(Hackmon hackmon) {
        this.hackmon = hackmon;
    }

    public void render(SpriteBatch batch){
        batch.draw(black,x-1,y-1,hackmon.getHp()+2,12);
        batch.draw(red,x,y,hackmon.getHp(),10);
        batch.draw(green,x,y,hackmon.getCurrHP(),10);
    }
}
