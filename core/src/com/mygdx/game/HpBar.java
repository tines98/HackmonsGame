package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HpBar {
    private Hackmon hackmon;
    private int y, x;
    private Texture green,red,black;

    public HpBar(int x, int y){
        this.x = x;
        this.y = y;
        black = new Texture(
"/home/tines/IdeaProjects/Hackmons/core/assets/black.png"
        );
        green = new Texture(
"/home/tines/IdeaProjects/Hackmons/core/assets/hpGreen.png"
        );
        red = new Texture(
"/home/tines/IdeaProjects/Hackmons/core/assets/hpRed.png"
        );
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
