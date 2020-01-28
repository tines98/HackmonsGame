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

    private int x,y,w,h;
    private int buttonWidth,buttonHeight, selected = 0;
    private BattleMenuButton[] buttons;
    private Texture gradient;
    private Trainer trainer;
    private String[] moveList;

    public FightMenu(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        gradient = new Texture("core/assets/gradient.png");
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
        input();
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
                return;
            }
            if (trainer.getSelected().getMoves()[selected].getCost() > trainer.getSelected().getCurrStam()) {
                BattleInfoBox.updateText(trainer.getSelected().getName() + " is too exhausted for this!");
            }
            else {
                TurnHandler.setAction(BattleAction.ATTACK);
                TurnHandler.setCurrentMove(trainer.getSelected().getMoves()[selected]);
                TurnHandler.setReady();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)){
            BattleMenu.isFightPressed = false;
        }
    }
}
