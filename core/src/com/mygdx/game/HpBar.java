package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HpBar {
    private Hackmon hackmon;
    private int y, x;
    private float size, greenSize, blueSize;
    private int prevHp, prevStam;

    public HpBar(int x, int y){
        this.x = x;
        this.y = y;
        size = 100;
        greenSize=size;
        blueSize=size;
    }

    //TODO update method name
    private void updateGreenSize(){
        greenSize = size * ((float)hackmon.getCurrHp()/(float)hackmon.getHp());
        blueSize = size * ((float)hackmon.getCurrStam()/(float)hackmon.getStam());
    }

    public void setHackmon(Hackmon hackmon) {
        this.hackmon = hackmon;
        prevHp = hackmon.getCurrHp();
    }

    public void render(SpriteBatch batch, BitmapFont font){
        if (hackmon.getCurrHp() != prevHp) {
            //Changed this form .getHp() to .getCurrHp() because I think that's correct?
            prevHp=hackmon.getCurrHp();
            updateGreenSize();
        }
        if (hackmon.getCurrStam() != prevStam) {
            prevStam = hackmon.getCurrStam();
            updateGreenSize();
        }
        batch.draw(Colors.black,x-1,y-1,size+2,15);
        batch.draw(Colors.red,x,y,size,13);
        batch.draw(Colors.green,x,y,greenSize,13);
        font.draw(batch,""+hackmon.getCurrHp()+"/"+hackmon.getHp(),x,y+12);

        batch.draw(Colors.black, x-1, y+15, size+2, 15);
        batch.draw(Colors.gray, x, y+16, size, 13);
        batch.draw(Colors.yellow, x, y+16, blueSize, 13);
        font.draw(batch, ""+hackmon.getCurrStam()+"/"+hackmon.getStam(), x, y+29);
    }
}
