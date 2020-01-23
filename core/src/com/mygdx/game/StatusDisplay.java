package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class StatusDisplay {
    int x, y, w, h;
    HpBar hp;
    StaminaBar stam;
    Trainer trainer;

    public StatusDisplay(Trainer trainer, int x, int y){
        this.trainer = trainer;
        this.x = x;
        this.y = y;
        w = 110;
        h = 70;
        hp = new HpBar(x+5,y+15+7);
        hp.setTrainer(trainer);
        stam = new StaminaBar(x+5,y+5);
        stam.setTrainer(trainer);
    }

    public void render(SpriteBatch batch, BitmapFont font){
        render(batch,font,trainer.getSelected());
    }

    public void render(SpriteBatch batch, BitmapFont font, Hackmon hackmon){
        ShapeDrawer.drawBox(batch,x,y,w,h);
        font.draw(
                batch
                ,hackmon.getName()
                ,x+(w/2)
                ,y+h-4
                ,0
                , Align.center
                ,false
        );
        font.draw(
                batch
                ,"LVL:"+hackmon.getLv()
                ,x+(w/2)
                ,y+h-15-4
                ,0
                ,Align.center
                ,false
        );
        hp.render(batch,font,hackmon);
        stam.render(batch,font,hackmon);
    }
}
