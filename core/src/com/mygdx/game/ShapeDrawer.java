package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShapeDrawer {

    public static void drawBox(SpriteBatch batch, int x, int y, int w, int h,
                               int padding, Texture color){
        batch.draw(Colors.black,x-padding,y-padding,w+padding*2,h+padding*2);
        batch.draw(color,x,y,w,h);
    }

    public static void drawBox(SpriteBatch batch, int x, int y, int w, int h,
                               int padding){
        drawBox(batch,x,y,w,h,padding,Colors.gray);
    }

    public static void drawBox(SpriteBatch batch, int x, int y, int w, int h,
                               Texture color){
        drawBox(batch,x,y,w,h,1,color);
    }

    public static void drawBox(SpriteBatch batch, int x, int y, int w, int h){
        drawBox(batch,x,y,w,h,1,Colors.gray);
    }
}
