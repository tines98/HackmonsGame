package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BattleMap {
    Trainer player, opponent;
    Texture bg;
    HpBar playerHpBar, playerStamBar, opponentHpBar, opponentStamBar;
    BattleMenu menu;
    FightMenu fightMenu;
    private int x1,y1,x2,y2;

    public BattleMap() {
        x1=25;
        y1=25;
        x2=800-96-25;
        y2=400-150-48-25;
        playerHpBar = new HpBar(x1,y1);
        opponentHpBar = new HpBar(x2,y2);
        menu = new BattleMenu(300,10,400,100);
        fightMenu = new FightMenu(300,10,400,100);
    }

    public void render(SpriteBatch batch, BitmapFont font){
        batch.draw(bg,0,0);
        player.getSelected().render(batch,x1,y1,2);
        opponent.getSelected().render(batch,x2,y2,2);
        playerHpBar.render(batch,font);
        opponentHpBar.render(batch,font);
        menu.render(batch, font);
    }

    public void setPlayer(Trainer trainer) {
        player = trainer;
        playerHpBar.setTrainer(player);
    }

    public void setOpponent(Trainer opponent) {
        this.opponent = opponent;
        opponentHpBar.setTrainer(opponent);
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }
}
