package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BattleMenu {

    private int x,y,width,height;
    private static final Texture gray =
        new Texture("core/assets/gray.png");
    private static final Texture black =
        new Texture("core/assets/black.png");
    private int button1x,button1y,button2x,button2y,button3x,button3y,
            button4x,button4y,buttonWidth,buttonHeight;
    private static final int offsetX = 3;
    private static final int offsetY = 3;


    public BattleMenu(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        buttonHeight=(height-16)/2;
        buttonWidth=(width-16)/2;
        button1x=x+6;
        button1y=y+6;
        button2x=x+12+buttonWidth;
        button2y=y+6;
        button3x=x+12+buttonWidth;
        button3y=y+12+buttonHeight;
        button4x=x+6;
        button4y=y+12+buttonHeight;
    }

    private void drawButton(SpriteBatch batch,int x, int y){
       batch.draw(black,x-offsetX,y-1,buttonWidth+offsetX+1,
               buttonHeight+offsetY);
       batch.draw(gray,x,y,buttonWidth,buttonHeight);
    }

    public void render(SpriteBatch batch){
        batch.draw(black,x-offsetX,y-1,width+offsetX+1,height+offsetY);
        batch.draw(gray,x,y,width,height);

        drawButton(batch,button1x,button1y);
        drawButton(batch,button2x,button2y);
        drawButton(batch,button3x,button3y);
        drawButton(batch,button4x,button4y);
    }
}
