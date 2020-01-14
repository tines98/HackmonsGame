package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BattleMenu {

    private int x,y,w,h;
    private int buttonWidth,buttonHeight, selected = 0;
    private BattleMenuButton fightBtn,runBtn,bagBtn,switchBtn;
    private static int FIGHT = 3;
    private static int RUN = 1;
    private static int SWITCH = 0;
    private static int BAG = 2;


    public BattleMenu(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        buttonHeight=(h-16)/2;
        buttonWidth=(w-16)/2;
        int padding = 6;
        switchBtn =
                new BattleMenuButton(x+padding,y+padding,buttonWidth, buttonHeight);
        runBtn =
                new BattleMenuButton(x+padding*2+buttonWidth,y+padding,buttonWidth,
                        buttonHeight);
        bagBtn =
                new BattleMenuButton(x+padding*2+buttonWidth,y+padding*2+buttonHeight,
                        buttonWidth, buttonHeight);
        fightBtn =
                new BattleMenuButton(x+padding,y+padding*2+buttonHeight,buttonWidth,
                        buttonHeight);
        switchBtn.setText("Switch");
        fightBtn.setText("Fight");
        runBtn.setText("Run");
        bagBtn.setText("Bag");
    }

    public void render(SpriteBatch batch, BitmapFont font){
        batch.draw(Colors.black,x-1,y-1,w+2,h+2);
        batch.draw(Colors.gray,x,y,w,h);
        input();
        if (selected==FIGHT)
            fightBtn.render(batch,font,true);
        else
            fightBtn.render(batch,font,false);
        if (selected==RUN)
            runBtn.render(batch,font,true);
        else
            runBtn.render(batch,font,false);
        if (selected==BAG)
            bagBtn.render(batch,font,true);
        else
            bagBtn.render(batch,font,false);
        if (selected==SWITCH)
            switchBtn.render(batch,font,true);
        else
            switchBtn.render(batch,font,false);
    }

    public void input(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (selected==0) selected=3;
            if (selected==1) selected=2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            if (selected==1) selected=0;
            if (selected==2) selected=3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if (selected==3) selected=0;
            if (selected==2) selected=1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            if (selected==0) selected=1;
            if (selected==3) selected=2;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)){
            if (selected==BAG){
                HackmonsGame.changeScreenState(ScreenState.BAGMENU);
            }
            if (selected==SWITCH){
                HackmonsGame.changeScreenState(ScreenState.SWITCHMENU);
            }
        }
    }
}
