package com.mygdx.game.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Colors;

public class BattleMenuButton {
    int x, y, w, h, textX, textY;
    Texture color;
    String text = "ERROR";

    public BattleMenuButton(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        textX = x+(w/2);
        textY = y+(h/2);
    }

    public BattleMenuButton(int x, int y, int w, int h, Texture color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void render(SpriteBatch batch, BitmapFont font, boolean active){
        batch.draw(Colors.black,x-1,y-1,w+2,h+2);
        if (active)
            batch.draw(Colors.darkGray,x,y,w,h);
        else batch.draw(Colors.gray,x,y,w,h);
        font.draw(batch,text,textX,textY+6,0,Align.center,false);
    }
}
