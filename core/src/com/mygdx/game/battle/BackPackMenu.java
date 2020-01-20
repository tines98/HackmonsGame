package com.mygdx.game.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.BackPack;
import com.mygdx.game.Colors;
import com.mygdx.game.HackmonsGame;
import com.mygdx.game.ScreenState;

public class BackPackMenu {
    BackPack backPack;
    int selected, item1x, item2x, item3x, itemY, itemW, itemH, timer, cooldown;


    public BackPackMenu(BackPack pack){
        backPack = pack;
        selected = 1;
        itemW = 300;
        itemH = 100;
        item2x = (Gdx.graphics.getWidth()-itemW)/2;
        item1x = item2x-itemW-50;
        item3x = item2x+itemW+50;
        itemY = Gdx.graphics.getHeight()-50-50-itemH;
        timer = 0;
        cooldown = 10;
    }

    private void renderItemBox(SpriteBatch batch,BitmapFont font,int x,int i,
                               Texture sprite){
        batch.draw(Colors.black,x-1,itemY-1,itemW+2,itemH+2);
        batch.draw(sprite,x,itemY,itemW,itemH);
        font.draw(
            batch,
            backPack.getContents().get(i).getName(),
            x+itemW/2,
            itemY+itemH/2,
            0,
            Align.center,
            false
        );
        backPack.getContents().get(i).render(
            batch,
            x+itemW/2-12,
            itemY+itemH/2-15-25,
            25,
            25
        );
    }

    private void renderItems(SpriteBatch batch, BitmapFont font){
        if (selected>0){
            renderItemBox(batch,font,item1x,selected-1,Colors.darkGray);
        }
        renderItemBox(batch,font,item2x,selected,Colors.gray);
        if (selected<backPack.getContents().size()-1){
            renderItemBox(batch,font,item3x,selected+1,Colors.darkGray);
        }
    }

    public void render(SpriteBatch batch, BitmapFont font){
        //RENDERS BACKGROUND COLOR
        batch.draw(Colors.yellow,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //RENDERS UPPER BAR AND BACKPACK TEXT
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
        renderItems(batch,font);
        batch.draw(Colors.darkGray,0,0,
                Gdx.graphics.getWidth(),50);
        font.draw(
                batch,
                backPack.getContents().get(selected).getDesc(),
                Gdx.graphics.getWidth()/2,
                50-10,
                0,
                Align.center,
                false
        );
        input();
    }

    private void input(){
        if (timer<=0) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                if (selected > 0) {
                    selected--;
                    timer = cooldown;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                if (selected < backPack.getContents().size()-1) {
                    selected++;
                    timer = cooldown;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.J)) {
                HackmonsGame.changeScreenState(ScreenState.BATTLEMENU);
            }

        }
        else timer--;
    }
}
