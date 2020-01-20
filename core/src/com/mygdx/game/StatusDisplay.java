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
        stam = new StaminaBar(x+5,y+3);
        stam.setTrainer(trainer);
        hp = new HpBar(x+5,y+15+6);
        hp.setTrainer(trainer);
    }

    public void render(SpriteBatch batch, BitmapFont font){
        ShapeDrawer.drawBox(batch,x,y,w,h);
        font.draw(
                batch
                ,trainer.getSelected().getName()
                ,x+(w/2)
                ,y+h-3
                ,0
                , Align.center
                ,false
        );
        font.draw(
                batch
                ,"LVL:"+trainer.getSelected().getLv()
                ,x+(w/2)
                ,y+h-15-3
                ,0
                ,Align.center
                ,false
        );
        hp.render(batch,font);
        stam.render(batch,font);
    }
}
