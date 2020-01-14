package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class SwitchHackmonMenu {
    Trainer trainer;
    int selected, item1x, item2x, item3x, itemY, itemW, itemH, timer, cooldown;


    public SwitchHackmonMenu(Trainer trnr){
        trainer = trnr;
        selected = 0;
        itemW = 225;
        itemH = 100;
        timer = 0;
        cooldown = 10;
    }

    private void renderHackmonBox(SpriteBatch batch, BitmapFont font, int x,
                                int y,
                               Hackmon hackmon, boolean sel){
        //DRAW BACKGROUND BOX
        batch.draw(Colors.black,x-1,y-1,itemW+2,itemH+2);
        if (sel)
            batch.draw(Colors.darkGray,x,y,itemW,itemH);
        else
            batch.draw(Colors.gray,x,y,itemW,itemH);
        //DRAW SPRITE
        hackmon.render(batch,x,y-5);
        //DRAW NAME
        font.draw(
            batch,
            hackmon.getName(),
            x+(itemW/2)+50,
            y+(itemH/2)+25,
            0,
            Align.center,
            false
        );
        //DRAW LEVEL
        font.draw(
            batch,
            "Lvl "+hackmon.getLv(),
            x+(itemW/2)+50,
            y+(itemH/2)+5,
            0,
            Align.center,
            false
        );
        //UPDATE/DRAW HP BAR
        HpBar hpBar = new HpBar(x+itemW/2,y+5);
        hpBar.setHackmon(hackmon);
        hpBar.render(batch,font);
    }

    private void renderItems(SpriteBatch batch, BitmapFont font){
        int x,y,w,h;
        boolean sel;
        for (int i = 0; i < trainer.getHackmons().size(); i++) {
            y = Gdx.graphics.getHeight()-(115*(i/2))-60-itemH;
            x = i%2==0 ? 75 : Gdx.graphics.getWidth() - 75 - itemW;
            if (selected==i) sel = true;
            else sel = false;
            renderHackmonBox(batch,font,x,y,trainer.getHackmons().get(i),sel);
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
                "Switch Pokemon",
                Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()-25,
                0,
                Align.center,
                false
        );
        renderItems(batch,font);
        input();
    }

    private void input(){
        if (timer<=0) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                selected = selected==0 ? trainer.getHackmons().size()-1 :
                        selected-1;
                timer = cooldown;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    selected = (selected+1)%trainer.getHackmons().size();
                    timer = cooldown;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.J)) {
                HackmonsGame.changeScreenState(ScreenState.BATTLEMENU);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
                trainer.switchMon(selected);
                HackmonsGame.changeScreenState(ScreenState.BATTLEMENU);
            }
        }
        else timer--;
    }
}
