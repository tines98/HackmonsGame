package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BattleMenu {

    private int x,y,w,h;
    private int button1x,button1y,button2x,button2y,button3x,button3y,
            button4x,button4y,buttonWidth,buttonHeight;
    private static final int offsetX = 3;
    private static final int offsetY = 3;
    private BattleMenuButton fightBtn,runBtn,bagBtn,switchBtn;


    public BattleMenu(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        buttonHeight=(h-16)/2;
        buttonWidth=(w-16)/2;
        int padding = 6;
        switchBtn =
                new BattleMenuButton(x+padding,y+padding,buttonWidth, buttonHeight);
        runBtn =
                new BattleMenuButton(x+padding*2+buttonWidth,y+padding,buttonWidth,
                        buttonHeight);
        bagBtn =
                new BattleMenuButton(x+padding*2+buttonWidth,y+padding*2+buttonHeight,
                        buttonWidth, buttonHeight);
        fightBtn =
                new BattleMenuButton(x+padding,y+padding*2+buttonHeight,buttonWidth,
                        buttonHeight);
        switchBtn.setText("Switch");
        fightBtn.setText("Fight");
        runBtn.setText("Run");
        bagBtn.setText("Bag");
    }

    public void render(SpriteBatch batch, BitmapFont font){
        batch.draw(Colors.black,x-1,y-1,w+2,h+2);
        batch.draw(Colors.gray,x,y,w,h);
        fightBtn.render(batch,font);
        runBtn.render(batch,font);
        bagBtn.render(batch,font);
        switchBtn.render(batch,font);
    }
}
