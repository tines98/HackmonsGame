package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//This is just a restruction of BattleMap, but didn't want to touch that class :)
public class BattleScene {

    private int x1, y1, x2, y2;
    Texture bg;
    BattleMenu menu;
    HpBar hpBar1, hpBar2;
    Trainer player, opponent;

    public BattleScene(Trainer player, Trainer opponent){
        this.player = player;
        this.opponent = opponent;
    }

    public void render(SpriteBatch batch, BitmapFont font){
        batch.draw(bg,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        player.getSelected().render(batch,x1,y1,2);
        opponent.getSelected().render(batch,x2,y2,2);
        hpBar1.render(batch,font);
        hpBar2.render(batch,font);
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }

}
