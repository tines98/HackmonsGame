package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class BackPackMenu {
    BackPack backPack;

    public BackPackMenu(BackPack pack){
        backPack = pack;
    }

    private void renderItems(){

    }

    public void render(SpriteBatch batch, BitmapFont font){
        batch.draw(Colors.yellow,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(Colors.darkGray,0,Gdx.graphics.getHeight()-50,
                Gdx.graphics.getWidth(),50);
        font.draw(
                batch,
                "Backpack",
                Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()-25,
                0,
                Align.center,
                false
        );
    }
}
