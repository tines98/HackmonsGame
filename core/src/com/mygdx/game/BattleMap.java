package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BattleMap {
    Trainer player, opponent;
    Texture bg;
    BattleMenu menu;
    FightMenu fightMenu;
    private int playerX, playerY, opponentX, opponentY;
    //TEST
    StatusDisplay playerStatusDisplay, opponentStatusDisplay;

    public BattleMap() {
        playerX = 50;
        playerY = 90;
        opponentX = 800-(96*2)-25;
        opponentY = 400-150-48-25;
        menu = new BattleMenu(350,10,400,100);
        fightMenu = new FightMenu(390,10,400,100);
    }

    public void render(SpriteBatch batch, BitmapFont font){
        batch.draw(bg,0,0);
        player.getSelected().render(batch, playerX, playerY,2);
        opponent.getSelected().render(batch, opponentX, opponentY,2);
        menu.render(batch, font);
        playerStatusDisplay.render(batch,font);
        opponentStatusDisplay.render(batch,font);
    }

    public void setPlayer(Trainer trainer) {
        player = trainer;
        //TEST
        playerStatusDisplay = new StatusDisplay(player,100,25);
    }

    public void setOpponent(Trainer opponent) {
        this.opponent = opponent;
        opponentStatusDisplay = new StatusDisplay(opponent, 450,250);
        opponent.getSelected().setToFront();
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }
}
