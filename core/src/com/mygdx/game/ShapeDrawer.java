package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class ShapeDrawer {

    public static void drawBox(SpriteBatch batch, int x, int y, int w, int h,
                               int padding, Texture color){
        batch.draw(Colors.black,x,y,w,h);
        batch.draw(color,x+padding,y+padding,w-padding*2,h-padding*2);
    }

    public static void drawBox(SpriteBatch batch, int x, int y, int w, int h,
                               int padding){
        drawBox(batch,x,y,w,h,padding,Colors.gray);
    }

    public static void drawBox(SpriteBatch batch, int x, int y, int w, int h,
                               Texture color){
        drawBox(batch,x,y,w,h,2,color);
    }

    public static void drawBox(SpriteBatch batch, int x, int y, int w, int h){
        drawBox(batch,x,y,w,h,2,Colors.gray);
    }

    public static void drawTextBox(SpriteBatch batch,
                                   BitmapFont font, String text,
                                   int x, int y, int w, int h,
                                   int padding, Texture color){
        drawBox(batch,x,y,w,h,padding,color);
        font.draw(batch,text,x+padding,y+h-padding,w, Align.left,true);
    }

    public static void drawTextBox(SpriteBatch batch,
                                   BitmapFont font, String text,
                                   int x, int y, int w, int h, Texture color){
        drawTextBox(batch,font,text,x,y,w,h,1,color);
    }

    public static void drawTextBox(SpriteBatch batch,
                                   BitmapFont font, String text,
                                   int x, int y, int w, int h, int padding){
        drawTextBox(batch,font,text,x,y,w,h,padding,Colors.gray);
    }

    public static void drawTextBox(SpriteBatch batch,BitmapFont font,String text,
                                   int x, int y, int w, int h){
        drawTextBox(batch,font,text,x,y,w,h,1,Colors.gray);
    }
}
