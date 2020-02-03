package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.battle.ExpBar;

public class StatusDisplay {
    int x, y, w, h;
    HpBar hp;
    StaminaBar stam;
    ExpBar exp;
    Trainer trainer;
    private static final Texture ball = new Texture("core/assets/pokeball.png");
    private static final Texture noBall = new Texture("core/assets/pokeballDead.png");

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
        exp = new ExpBar(x+5,y-5);
    }

    public void render(SpriteBatch batch, BitmapFont font){
        render(batch,font,trainer.getSelected());
    }

    public void render(SpriteBatch batch, BitmapFont font, Hackmon hackmon){
        ShapeDrawer.drawBox(batch,x,y,w,h);
        for (int i = 0; i < trainer.getParty().size(); i++) {
            if (trainer.getParty().get(i).isFainted()){
                batch.draw(noBall, x+5+(i*(w/trainer.getParty().size())),
                        y + h-5);
            }
            else
                batch.draw(ball, x+5+(i*(w/trainer.getParty().size())),
                    y + h-5);
        }
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
        exp.render(batch,font,hackmon);

        //TEST
        if (trainer.getSelected().getStatus() != StatusEffect.NONE) {
            batch.draw(StatusEffect.getSprite(trainer.getSelected().getStatus()),x+5,y+37,20,20);
        }
    }
}
