package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.battle.BattleAction;
import com.mygdx.game.battle.TurnHandler;

public class SwitchHackmonMenu {
    Trainer player;
    int selected, itemW, itemH;


    public SwitchHackmonMenu(Trainer trnr){
        player = trnr;
        selected = 1;
        itemW = 9*HackmonsGame.SCREENWIDTH/32;
        itemH = HackmonsGame.SCREENHEIGHT/4;
    }

    private void renderHackmonBox(SpriteBatch batch, BitmapFont font, int x, int y,
                               Hackmon hackmon, boolean sel){
        //DRAW BACKGROUND BOX
        if (sel)
            ShapeDrawer.drawBox(batch,x,y,itemW,itemH,Colors.darkGray);
        else
            ShapeDrawer.drawBox(batch,x,y,itemW,itemH);
        //DRAW SPRITE
        hackmon.setToFront();
        hackmon.render(batch,x,y,2);
        hackmon.setToBack();
        //UPDATE/DRAW HP BAR
        StatusDisplay statusDisplay = new StatusDisplay(player,x+itemW/2, y+5);
        statusDisplay.render(batch,font,hackmon);
    }

    private void renderItems(SpriteBatch batch, BitmapFont font){
        int x,y = (HackmonsGame.SCREENHEIGHT-itemH)/8;
        boolean sel;
        for (int i = 0; i < player.getParty().size()-1; i++) {
            x = i*100+i*25+25;
            if (selected==i+1) continue;
            renderHackmonBox(batch,font,x,y, player.getParty().get(i+1),
                    false);
        }
        renderHackmonBox(batch,font,(HackmonsGame.SCREENWIDTH-itemW)/2,
                (HackmonsGame.SCREENHEIGHT*2-itemH)/8,
                player.getParty().get(selected),
                true);
        //BRUH
        renderHackmonBox(batch,font,(HackmonsGame.SCREENWIDTH-itemW)/2,
                (HackmonsGame.SCREENHEIGHT*5-itemH)/8, player.getSelected(),
                false);
    }

    public void render(SpriteBatch batch, BitmapFont font){
        //RENDERS BACKGROUND COLOR
        batch.draw(Colors.yellow, 0,0,HackmonsGame.SCREENWIDTH,
                HackmonsGame.SCREENHEIGHT);
        //RENDERS UPPER BAR AND BACKPACK TEXT
        ShapeDrawer.drawBox(batch,0,7*HackmonsGame.SCREENHEIGHT/8,
                HackmonsGame.SCREENWIDTH,HackmonsGame.SCREENHEIGHT/8);
        font.draw(
            batch,
            "Switch Pokemon",
            HackmonsGame.SCREENWIDTH/2,
            HackmonsGame.SCREENHEIGHT-25,
            0,
            Align.center,
            false
        );
        renderItems(batch,font);
        input();
    }

    private void input(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            selected = selected==1 ? player.getParty().size()-1 :
                    selected-1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
                selected = (selected+1)% player.getParty().size();
                if (selected==0)selected = 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
            HackmonsGame.changeScreenState(ScreenState.BATTLEMENU);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            if (!player.getMon(selected).isFainted()) {
                if (player.getSelected().isFainted()) {
                    player.switchMon(selected);
                    HackmonsGame.changeScreenState(ScreenState.BATTLEMENU);
                }
                else {
                    player.switchMon(selected);
                    HackmonsGame.changeScreenState(ScreenState.BATTLEMENU);
                    TurnHandler.setAction(BattleAction.SWITCH);
                    TurnHandler.setReady();
                }
            }
        }
    }
}
