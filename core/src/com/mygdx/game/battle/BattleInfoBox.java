package com.mygdx.game.battle;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Colors;
import com.mygdx.game.ShapeDrawer;

public class BattleInfoBox {

    private int x, y, w, h;
    String text = "Hello Buddah";

    public BattleInfoBox(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void updateText(String newText) {
        text = newText;
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        batch.draw(Colors.black, x, y, w+1, h+1);
        ShapeDrawer.drawBox(batch, x, y, w-1, h-1, Colors.gray);
        font.draw(batch, text, x+4, h-4, 0, Align.topLeft, false);
    }
}
