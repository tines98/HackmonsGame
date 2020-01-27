package com.mygdx.game.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.*;

public class BattleMap {
    Trainer player, opponent;
    Texture bg;
    BattleMenu menu;
    FightMenu fightMenu;
    private int playerX, playerY, opponentX, opponentY;
    StatusDisplay playerStatusDisplay, opponentStatusDisplay;

    public BattleMap() {
        playerX = 50;
        playerY = 90;
        opponentX = 800-(96*2)-25;
        opponentY = 400-150-48-25;
        menu = new BattleMenu(400,0,400,100);
        fightMenu = new FightMenu(400,0,400,100);
    }

    //THIS IS THE MAIN LOOP
    public void render(SpriteBatch batch, BitmapFont font){
        batch.draw(bg,0,0);
        player.getSelected().render(batch, playerX, playerY,2);
        opponent.getSelected().render(batch, opponentX, opponentY,2);
        if (menu.isFightPressed){
            fightMenu.setTrainer(player);
            fightMenu.render(batch,font);
        }
        else
            menu.render(batch, font);
        playerStatusDisplay.render(batch,font);
        opponentStatusDisplay.render(batch,font);

        BattleInfoBox.render(batch, font);

        if (TurnHandler.isReady()) {
            switch (TurnHandler.getAction()) {
                //ATTACK
                case 0:
                    BattleLogic.turnAttack();
                    break;
                //ITEM, SWITCH; RUN AWAY
                case 1:
                case 2:
                    BattleInfoBox.updateText("Player switched into " + player.getSelected().getName() + "!");
                case 3:
                    BattleLogic.turnPass();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            TurnHandler.unReady();
        }
    }

    public void setPlayer(Trainer newTrainer) {
        player = newTrainer;
        playerStatusDisplay = new StatusDisplay(player,275,125);
        BattleLogic.setPlayer(player);
    }

    public void setOpponent(Trainer newOpponent) {
        this.opponent = newOpponent;
        opponentStatusDisplay = new StatusDisplay(opponent, 450,250);
        opponent.getSelected().setToFront();
        BattleLogic.setOpponent(opponent);
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }
}
