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
                    turnAttack();
                    break;
                //ITEM, SWITCH; RUN AWAY
                case 1:
                case 2:
                    BattleInfoBox.updateText("Player switched into " + player.getSelected().getName() + "!");
                case 3:
                    turnPass();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            TurnHandler.unReady();
        }
    }

    public void turnAttack() {
        if (TurnHandler.getCurrentMove().getPriority() > Opponent.doTurn().getPriority()) {
            playerFirst();
        }
        else if (TurnHandler.getCurrentMove().getPriority() < Opponent.doTurn().getPriority()) {
            opponentFirst();
        }
        else {
            if (player.getSelected().getSpeed() > opponent.getSelected().getSpeed()) {
                playerFirst();
            }
            else if (player.getSelected().getSpeed() < opponent.getSelected().getSpeed()){
                opponentFirst();
            }
            else {
                if (RNG.chance(50)) {
                    playerFirst();
                }
                else {
                    opponentFirst();
                }
            }
        }
    }

    public void playerFirst() {
        BattleInfoBox.updateText(
                Attack.attack(player.getSelected(), opponent.getSelected(), TurnHandler.getCurrentMove()));
        if (opponent.getSelected().isFainted()) {
            opponent.switchMon(opponent.nextMon());
            opponent.getSelected().setToFront();
        }
        Attack.attack(opponent.getSelected(), player.getSelected(), Opponent.doTurn());
        if (player.getSelected().isFainted()) {
            HackmonsGame.changeScreenState(ScreenState.SWITCHMENU);
        }
    }

    public void opponentFirst() {
        Attack.attack(opponent.getSelected(), player.getSelected(), Opponent.doTurn());
        if (player.getSelected().isFainted()) {
            HackmonsGame.changeScreenState(ScreenState.SWITCHMENU);
        }
        BattleInfoBox.updateText(
                Attack.attack(player.getSelected(), opponent.getSelected(), TurnHandler.getCurrentMove()));
        if (opponent.getSelected().isFainted()) {
            opponent.switchMon(opponent.nextMon());
            opponent.getSelected().setToFront();
        }
    }

    public void turnPass() {
        Attack.attack(opponent.getSelected(), player.getSelected(), Opponent.doTurn());
        player.getSelected().restoreStam(player.getSelected().getStam() / 8);
    }

    public void setPlayer(Trainer trainer) {
        player = trainer;
        playerStatusDisplay = new StatusDisplay(player,275,125);
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
