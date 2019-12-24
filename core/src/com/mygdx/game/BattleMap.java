package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BattleMap {
    Hackmon hackmon1, hackmon2;
    Texture bg;
    HpBar hpBar1,hpBar2;
    BattleMenu menu;
    private int x1,y1,x2,y2;

    public BattleMap(){
        x1=25;
        y1=25;
        x2=800-96-25;
        y2=400-150-48-25;
        hpBar1 = new HpBar(x1,y1);
        hpBar2 = new HpBar(x2,y2);
    }

    public void render(SpriteBatch batch){
        batch.draw(bg,0,0);
        hackmon1.render(batch,x1,y1);
        hackmon2.render(batch,x2,y2);
        hpBar1.render(batch);
        hpBar2.render(batch);
    }

    public void setHackmon1(Hackmon hackmon1) {
        this.hackmon1 = hackmon1;
        hpBar1.setHackmon(this.hackmon1);

    }

    public void setHackmon2(Hackmon hackmon2) {
        this.hackmon2 = hackmon2;
        this.hackmon2.setToFront();
        hpBar2.setHackmon(this.hackmon2);
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }
}
