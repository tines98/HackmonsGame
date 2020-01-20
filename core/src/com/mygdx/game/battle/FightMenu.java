package com.mygdx.game.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.*;
import com.mygdx.game.battle.BattleMenuButton;

import java.util.ArrayList;

public class FightMenu {

    private int x,y,w,h;
    private int buttonWidth,buttonHeight, selected = 0;
    private BattleMenuButton button0, button1, button2, button3;
    private ArrayList<BattleMenuButton> buttons;
    private Trainer trainer;
    private Hackmon hackmon;


    public FightMenu(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        buttonHeight=(h-16)/2;
        buttonWidth=(w-16)/2;
        int padding = 6;
        button3 =
                new BattleMenuButton(x+padding,y+padding,buttonWidth, buttonHeight);
        button1 =
                new BattleMenuButton(x+padding*2+buttonWidth,y+padding,buttonWidth,
                        buttonHeight);
        button2 =
                new BattleMenuButton(x+padding*2+buttonWidth,y+padding*2+buttonHeight,
                        buttonWidth, buttonHeight);
        button0 =
                new BattleMenuButton(x+padding,y+padding*2+buttonHeight,buttonWidth,
                        buttonHeight);
        button3.setText("ERROR");
        button0.setText("ERROR");
        button1.setText("ERROR");
        button2.setText("ERROR");
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
        update();
    }

    private void update(){
        hackmon = trainer.getSelected();
        Move[] moves = hackmon.getMoves();
        button0.setText(moves[0].getName());
        button1.setText(moves[1].getName());
        button2.setText(moves[2].getName());
        button3.setText(moves[3].getName());
    }

    public void render(SpriteBatch batch, BitmapFont font){
        batch.draw(Colors.black,x-1,y-1,w+2,h+2);
        batch.draw(Colors.gray,x,y,w,h);
        if (!hackmon.equals(trainer.getSelected())) {
            update();
        }
        input();
        if (selected==0)
            button0.render(batch,font,true);
        else
            button0.render(batch,font,false);
        if (selected==1)
            button1.render(batch,font,true);
        else
            button1.render(batch,font,false);
        if (selected==2)
            button2.render(batch,font,true);
        else
            button2.render(batch,font,false);
        if (selected==3)
            button3.render(batch,font,true);
        else
            button3.render(batch,font,false);
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
            if (selected==0){
                HackmonsGame.changeScreenState(ScreenState.BAGMENU);
            }
            if (selected==1){
                HackmonsGame.changeScreenState(ScreenState.SWITCHMENU);
            }
            if (selected==2){
                HackmonsGame.changeScreenState(ScreenState.SWITCHMENU);
            }
            if (selected==3){
                System.exit(1);
            }
        }
    }
}
