package com.mygdx.game.battle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Hackmon;
import com.mygdx.game.StatusDisplay;
import com.mygdx.game.Trainer;

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
        menu = new BattleMenu(350,10,400,100);
        fightMenu = new FightMenu(350,10,400,100);
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

        if (TurnHandler.isReady()) {
            switch (TurnHandler.getAction()) {
                //ATTACK
                case 0:
                    turnAttack();
                    break;
                //ITEM
                case 1:
                    turnPass();
                    break;
                //SWITCH
                case 2:
                    turnPass();
                    break;
                //RUNAWAY
                case 3:
                    turnPass();
                    break;
                default:
                    turnAttack();
                    break;
            }
            TurnHandler.unReady();
        }
    }

    public void turnAttack() {
        if (TurnHandler.getCurrentMove().getPriority() > Opponent.doTurn().getPriority()) {
            if (player.getSelected().getSpeed() > opponent.getSelected().getSpeed()) {
                Attack.attack(player.getSelected(), opponent.getSelected(), TurnHandler.getCurrentMove());
                Attack.attack(opponent.getSelected(), player.getSelected(), Opponent.doTurn());
            }
            else {
                Attack.attack(opponent.getSelected(), player.getSelected(), Opponent.doTurn());
                Attack.attack(player.getSelected(), opponent.getSelected(), TurnHandler.getCurrentMove());
            }
        }
        else {
            Attack.attack(opponent.getSelected(), player.getSelected(), Opponent.doTurn());
            Attack.attack(player.getSelected(), opponent.getSelected(), TurnHandler.getCurrentMove());
        }
    }

    public void turnPass() {
        Attack.attack(opponent.getSelected(), player.getSelected(), Opponent.doTurn());
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
