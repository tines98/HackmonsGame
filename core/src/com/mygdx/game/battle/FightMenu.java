package com.mygdx.game.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.*;

public class FightMenu {

    private int x,y,w,h;
    private int buttonWidth,buttonHeight, selected = 0;
    private BattleMenuButton[] buttons;
    private Trainer trainer;
    private Hackmon hackmon;


    public FightMenu(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        buttonHeight=(h-16)/2;
        buttonWidth=(w-16)/2;
        int padding = 5;
        buttons = new BattleMenuButton[]{
                new BattleMenuButton(x+padding,y+padding*2+buttonHeight,buttonWidth,
                        buttonHeight),
                new BattleMenuButton(x+padding*2+buttonWidth,y+padding*2+buttonHeight,
                        buttonWidth, buttonHeight),
                new BattleMenuButton(x+padding,y+padding,buttonWidth,
                        buttonHeight),
                new BattleMenuButton(x+padding*2+buttonWidth,y+padding,buttonWidth,
                        buttonHeight)
        };
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("ERROR");
        }
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
        update();
    }

    private void update(){
        hackmon = trainer.getSelected();
        Move[] moves = hackmon.getMoves();
        for (int i = 0; i < moves.length; i++) {
            if (moves[i] != null){
                buttons[i].setText(moves[i].getName());
            }
        }
    }

    public void render(SpriteBatch batch, BitmapFont font){
        ShapeDrawer.drawBox(batch,x,y,w,h);
        if (!hackmon.equals(trainer.getSelected())) {
            update();
        }
        input();
        if (selected==0)
            buttons[0].render(batch,font,true);
        else
            buttons[0].render(batch,font,false);
        if (selected==1)
            buttons[1].render(batch,font,true);
        else
            buttons[1].render(batch,font,false);
        if (selected==2)
            buttons[2].render(batch,font,true);
        else
            buttons[2].render(batch,font,false);
        if (selected==3)
            buttons[3].render(batch,font,true);
        else
            buttons[3].render(batch,font,false);
    }

    public void input(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (selected==2) selected=0;
            if (selected==3) selected=1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            if (selected==1) selected=0;
            if (selected==3) selected=2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if (selected==0) selected=2;
            if (selected==1) selected=3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            if (selected==0) selected=1;
            if (selected==2) selected=3;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)){
            if (trainer.getSelected().getMoves()[selected].getCost() > trainer.getSelected().getCurrStam()) {
                BattleInfoBox.updateText(trainer.getSelected().getName() + " is too exhausted for this!");
            }
            else {
                TurnHandler.setAction(0);
                TurnHandler.setCurrentMove(trainer.getSelected().getMoves()[selected]);
                TurnHandler.setReady();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)){
            BattleMenu.isFightPressed = false;
        }
    }
}
