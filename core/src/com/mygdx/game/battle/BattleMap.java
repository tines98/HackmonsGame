package com.mygdx.game.battle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Hackmon;
import com.mygdx.game.StatusDisplay;
import com.mygdx.game.Trainer;

public class BattleMap {
    Trainer player, opponent;
    Hackmon playerMon, opponentMon;
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
        menu = new BattleMenu(350,10,400,100);
        fightMenu = new FightMenu(390,10,400,100);
    }

    //THIS IS THE MAIN LOOP
    public void render(SpriteBatch batch, BitmapFont font){
        batch.draw(bg,0,0);
        player.getSelected().render(batch, playerX, playerY,2);
        opponent.getSelected().render(batch, opponentX, opponentY,2);
        menu.render(batch, font);
        playerStatusDisplay.render(batch,font);
        opponentStatusDisplay.render(batch,font);

        if (TurnHandler.isReady()) {
            TurnHandler.unReady();
            switch (TurnHandler.getAction()) {
                case 0:
                    TurnHandler.setCurrentMove(player.getSelected().getMoves()[0]);
                    turnAttack();
                    break;
                case 1:

                case 2:

                case 3:

                default:
                    turnAttack();
                    break;
            }
        }
    }

    public void turnAttack() {
        if (player.getSelected().getSpeed() > opponent.getSelected().getSpeed()) {
            Attack.attack(playerMon, opponentMon, TurnHandler.getCurrentMove());
            Attack.attack(opponentMon, playerMon, Opponent.doTurn());
        }
        else {
            Attack.attack(opponentMon, playerMon, Opponent.doTurn());
            Attack.attack(playerMon, opponentMon, TurnHandler.getCurrentMove());
        }
    }

    public void setPlayer(Trainer trainer) {
        player = trainer;
        //TEST
        playerStatusDisplay = new StatusDisplay(player,100,25);
        playerMon = player.getSelected();
    }

    public void setOpponent(Trainer opponent) {
        this.opponent = opponent;
        opponentStatusDisplay = new StatusDisplay(opponent, 450,250);
        opponent.getSelected().setToFront();
        opponentMon = opponent.getSelected();
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }
}
