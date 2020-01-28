package com.mygdx.game.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.*;
import com.mygdx.game.items.BackPack;

public class BackPackMenu {
    BackPack backPack;
    Trainer trainer;
    int selected, item1x, item2x, item3x, itemY, itemW, itemH;


    public BackPackMenu(BackPack pack, Trainer trainer){
        backPack = pack;
        selected = 1;
        itemW = 300;
        itemH = 100;
        item2x = (800-itemW)/2;
        item1x = item2x-itemW-50;
        item3x = item2x+itemW+50;
        itemY = 400-50-50-itemH;
        this.trainer = trainer;
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
        if (backPack.getContents().isEmpty());
        else if (backPack.getContents().size()==1){
            selected=0;
            renderItemBox(batch,font,item2x,selected,Colors.gray);
        }
        else {
            if (selected > 0) {
                renderItemBox(batch, font, item1x, selected - 1, Colors.darkGray);
            }
            renderItemBox(batch, font, item2x, selected, Colors.gray);
            if (selected < backPack.getContents().size() - 1) {
                renderItemBox(batch, font, item3x, selected + 1, Colors.darkGray);
            }
        }
    }

    public void render(SpriteBatch batch, BitmapFont font){
        //RENDERS BACKGROUND COLOR
        batch.draw(Colors.yellow,0,0, 800,400);
        //RENDERS UPPER BAR AND BACKPACK TEXT
        batch.draw(Colors.darkGray,0,400-50,
                Gdx.graphics.getWidth(),50);
        font.draw(
                batch,
                "Backpack",
                800/2,
                400-25,
                0,
                Align.center,
                false
        );
        renderItems(batch,font);
        batch.draw(Colors.darkGray,0,0,800,50);
        font.draw(
                batch,
                backPack.getContents().get(selected).getDesc(),
                800/2,
                50-10,
                0,
                Align.center,
                false
        );
        input();
    }

    private void input(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (selected > 0) {
                selected--;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (selected < backPack.getContents().size()-1) {
                selected++;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
            HackmonsGame.changeScreenState(ScreenState.BATTLEMENU);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            backPack.popItem(selected).useItem(trainer.getSelected());
            selected=0;
            TurnHandler.setAction(BattleAction.ITEM);
            TurnHandler.setReady();
            HackmonsGame.changeScreenState(ScreenState.BATTLEMENU);
        }
    }
}
