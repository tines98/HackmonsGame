package com.mygdx.game.battle;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Colors;
import com.mygdx.game.HackmonsGame;
import com.mygdx.game.ShapeDrawer;

public class BattleInfoBox {

    static private int x = 0;
    static private int y = 0;
    static private int w = HackmonsGame.SCREENWIDTH/2;
    static private int h = HackmonsGame.SCREENHEIGHT/4;
    static private int timer=0;
    static private int nextDelay;
    static private String text = "Hello Buddah";
    static private String nextText = text;

    public static void updateText(String newText) {
        updateText(newText,0);
    }

    public static void updateText(String newText, int delay){
        nextDelay = delay;
        nextText = newText;
    }

    public static void flush(){
        nextText = "";
    }

    public static void addToText(String newLine){
        if (nextText.equals("")){
            nextText = newLine;
        }
        else {
            nextText = nextText + "\n" + newLine;
        }
    }

    public static void render(SpriteBatch batch, BitmapFont font) {
        if (timer==0){
            text = nextText;
            timer = nextDelay;
            nextDelay=0;
        }
        else timer--;
        ShapeDrawer.drawBox(batch, x, y, w, h, Colors.gray);
        font.draw(batch, text, x+4, h-4, w, Align.topLeft, false);
    }

}
