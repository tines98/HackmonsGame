package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HpBar {
    private Hackmon hackmon;
    private int y, x;
    private float size, greenSize;
    private int prevHp;

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
        prevHp = hackmon.getCurrHP();
    }

    public void render(SpriteBatch batch, BitmapFont font){
        if (hackmon.getCurrHP() != prevHp) {
            prevHp=hackmon.getHp();
            updateGreenSize();
        }
        batch.draw(Colors.black,x-1,y-1,size+2,15);
        batch.draw(Colors.red,x,y,size,13);
        batch.draw(Colors.green,x,y,greenSize,13);
        font.draw(batch,""+hackmon.getCurrHP()+"/"+hackmon.getHp(),x,y+12);
    }
}
