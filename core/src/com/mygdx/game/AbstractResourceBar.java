package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public abstract class AbstractResourceBar {
    protected Trainer trainer;
    protected int y, x;
    protected float currSize, maxSize;
    protected float height;
    protected int prevResourceAmount;
    public AbstractResourceBar(int x, int y){
        this.x = x;
        this.y = y;
        maxSize = HackmonsGame.SCREENWIDTH/8-10;
        height = 13;
        currSize = maxSize;
    }

    protected abstract Texture getForeground();

    protected abstract Texture getBackground();

    /*
        Sets the Hackmon the HpBar shall represent.
         */
    public abstract void setTrainer(Trainer trainer);

    public abstract void checkChange(Hackmon hackmon);

    public abstract String getText(Hackmon hackmon);

    public void render(SpriteBatch batch, BitmapFont font){
        render(batch,font,trainer.getSelected());
    }

    public void render(SpriteBatch batch, BitmapFont font, Hackmon hackmon){
        checkChange(hackmon);
        batch.draw(Colors.black,x-1,y-1,maxSize+2,height);
        batch.draw(getBackground(),x,y,maxSize,height);
        batch.draw(getForeground(),x,y,currSize,height);
        font.draw(batch,getText(hackmon),x+(maxSize/2),y+12,0,
                Align.center,
                false);
    }
}
