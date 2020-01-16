package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BattleMap {
    Trainer trainer1, trainer2;
    Texture bg;
    HpBar hpBar1,hpBar2;
    BattleMenu menu;
    FightMenu fightMenu;
    private int x1,y1,x2,y2;

    public BattleMap() {
        x1=25;
        y1=25;
        x2=800-96-25;
        y2=400-150-48-25;
        hpBar1 = new HpBar(x1,y1);
        hpBar1.setColors(Colors.green,Colors.red);
        hpBar2 = new HpBar(x2,y2);
        hpBar2.setColors(Colors.green,Colors.red);
        menu = new BattleMenu(300,10,400,100);
        fightMenu = new FightMenu(300,10,400,100);
    }

    public void render(SpriteBatch batch, BitmapFont font){
        batch.draw(bg,0,0);
        trainer1.getSelected().render(batch,x1,y1,2);
        trainer2.getSelected().render(batch,x2,y2,2);
        hpBar1.render(batch,font);
        hpBar2.render(batch,font);
        menu.render(batch, font);
    }

    public void setTrainer1(Trainer trainer) {
        trainer1 = trainer;
        hpBar1.setTrainer(trainer1);
    }


    public void setTrainer2(Trainer trainer2) {
        this.trainer2 = trainer2;
        hpBar2.setTrainer(trainer2);
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }
}
