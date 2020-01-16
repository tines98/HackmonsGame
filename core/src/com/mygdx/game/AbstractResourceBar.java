package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractResourceBar {
    protected Trainer trainer;
    protected int y, x;
    protected float currSize, maxSize;
    protected int prevResourceAmount;
    protected Texture foreground, background;

    public AbstractResourceBar(int x, int y){
        this.x = x;
        this.y = y;
        maxSize = 100;
        currSize = maxSize;
    }

    public void setColors(Texture foreground, Texture background) {
        this.background = background;
        this.foreground = foreground;
    }

    /*
    Sets the Hackmon the HpBar shall represent.
     */
    public abstract void setTrainer(Trainer trainer);

    public abstract void checkChange();

    public abstract String getText();

    public void render(SpriteBatch batch, BitmapFont font){
        checkChange();
        batch.draw(Colors.black,x-1,y-1,maxSize+2,15);
        batch.draw(background,x,y,maxSize,13);
        batch.draw(foreground,x,y,currSize,13);
        font.draw(batch, getText(), x, y+29);
    }
}
