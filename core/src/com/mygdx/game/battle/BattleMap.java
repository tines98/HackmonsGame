package com.mygdx.game.battle;

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
                case ATTACK:
                    BattleLogic.turn(BattleAction.ATTACK, BattleAction.ATTACK);
                    break;
                //ITEM, SWITCH; RUN AWAY
                case ITEM:
                    BattleLogic.turn(BattleAction.ITEM, BattleAction.ATTACK);
                    break;
                case SWITCH:
                    BattleInfoBox.addToText("Player switched into " + player.getSelected().getName() + "!");
                    BattleLogic.turn(BattleAction.SWITCH, BattleAction.ATTACK);
                    break;
                case FLEE:
                    BattleLogic.turn(BattleAction.FLEE, BattleAction.ATTACK);
                    break;
                case REST:
                    BattleLogic.turn(BattleAction.REST, BattleAction.ATTACK);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            TurnHandler.unReady();
        }
    }

    public void setPlayer(Trainer newPlayer) {
        player = newPlayer;
        playerStatusDisplay = new StatusDisplay(player,275,125);
        fightMenu.setTrainer(player);
        BattleLogic.setPlayer(player);
    }

    public void setOpponent(Trainer newOpponent) {
        opponent = newOpponent;
        opponentStatusDisplay = new StatusDisplay(opponent, 450,250);
        opponent.getSelected().setToFront();
        BattleLogic.setOpponent(opponent);
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }
}
