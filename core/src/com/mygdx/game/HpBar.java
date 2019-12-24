package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HpBar {
    private Hackmon hackmon;
    private int y, x;
    private float size, greenSize;
    private int prevHp;
    private static final Texture green =
        new Texture("core/assets/hpGreen.png");
    private static final Texture red =
        new Texture("core/assets/hpRed.png");
    private static final Texture black =
        new Texture("core/assets/black.png");
    private static final BitmapFont font = new BitmapFont();

    public HpBar(int x, int y){
        this.x = x;
        this.y = y;
        size = 100;
        greenSize=size;
    }

    private void updateGreenSize(){
        greenSize = size * ((float)hackmon.getCurrHP()/(float)hackmon.getHp());
    }

    public void setHackmon(Hackmon hackmon) {
        this.hackmon = hackmon;
        prevHp = hackmon.getHp();
    }

    public void render(SpriteBatch batch){
        if (hackmon.getHp() != prevHp) {
            prevHp=hackmon.getHp();
            updateGreenSize();
        }
        batch.draw(black,x-1,y-1,size+2,15);
        batch.draw(red,x,y,size,13);
        batch.draw(green,x,y,greenSize,13);
        font.draw(batch,""+hackmon.getCurrHP()+"/"+hackmon.getHp(),x,y+13);
    }
}
