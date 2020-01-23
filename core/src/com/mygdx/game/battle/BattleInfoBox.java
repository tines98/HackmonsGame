package com.mygdx.game.battle;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Colors;
import com.mygdx.game.ShapeDrawer;

public class BattleInfoBox {

    static private int x = 0;
    static private int y = 0;
    static private int w = 400;
    static private int h = 100;
    static String text = "Hello Buddah";

    public static void updateText(String newText) {
        text = newText;
    }

    public static void render(SpriteBatch batch, BitmapFont font) {
        ShapeDrawer.drawBox(batch, x, y, w, h, Colors.gray);
        font.draw(batch, text, x+4, h-4, 0, Align.topLeft, false);
    }

}
