package com.mygdx.game.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.*;

public class FightMenu {

    private int x,y,w,h,selected = 0;
    private Texture gradient;
    private Trainer trainer;
    private String[] moveList;

    public FightMenu(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        gradient = new Texture("core/assets/gradient.png");
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    private void update(){
        moveList = new String[trainer.getSelected().getMoves().length+1];
        for (int i = 0; i < trainer.getSelected().getMoves().length; i++) {
            if (trainer.getSelected().getMoves()[i]==null){
                moveList[i] = "Rest";
            }
            else
                moveList[i] = trainer.getSelected().getMoves()[i].getName();
        }
        moveList[trainer.getSelected().getMoves().length] = "Rest";
    }

    private void infoBox(Move move){
        BattleInfoBox.updateText(
                "Type:"+move.getType()
                +"\n"+
                "Power: "+move.getPower()
                +"\n"+
                "Acc:"+move.getAccuarcy()
                +"\n"+
                "SP Cost:"+move.getCost()
            );
    }

    public void render(SpriteBatch batch, BitmapFont font){
        batch.draw(gradient,400,0,400,100);
        update();
        if (selected > 0){
            font.setColor(Color.GRAY);
            font.draw(
                batch,
                moveList[selected-1],
                400+100,
                50,
                0,
                Align.center,
                true
            );
            font.setColor(Color.WHITE);
        }
        font.draw(
            batch,
            moveList[selected],
            400+200,
            50+10,
            0,
            Align.center,
            true
        );
        if (moveList[selected].equals("ERROR")
                || moveList[selected].equals("Rest")){
            BattleInfoBox.updateText("Skip your turn to restore some stamina");
        }
        else infoBox(trainer.getSelected().getMoves()[selected]);
        if (selected<4){
            font.setColor(Color.GRAY);
            font.draw(
                batch,
                moveList[selected+1],
                400+300,
                50,
                0,
                Align.center,
                true
            );
            font.setColor(Color.WHITE);
        }
        input();
    }

    public void input(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)){
            if (selected>0) selected--;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)){
            if (selected<4) selected++;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)){
            if (moveList[selected].equals("Rest")){
                TurnHandler.setAction(BattleAction.REST);
                TurnHandler.setReady();
                BattleMenu.isFightPressed = false;
                return;
            }
            if (trainer.getSelected().getMoves()[selected].getCost() > trainer.getSelected().getCurrStam()) {
                BattleInfoBox.updateText(trainer.getSelected().getName() + " is too " +
                        "exhausted for this!",60);
            }
            else {
                TurnHandler.setAction(BattleAction.ATTACK);
                TurnHandler.setCurrentMove(trainer.getSelected().getMoves()[selected]);
                TurnHandler.setReady();
                BattleMenu.isFightPressed = false;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)){
            BattleMenu.isFightPressed = false;
        }
    }
}
