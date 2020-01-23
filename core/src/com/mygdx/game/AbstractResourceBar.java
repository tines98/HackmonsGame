package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public abstract class AbstractResourceBar {
    protected Trainer trainer;
    protected int y, x;
    protected float currSize, maxSize;
    protected int prevResourceAmount;
    public AbstractResourceBar(int x, int y){
        this.x = x;
        this.y = y;
        maxSize = 100;
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
        batch.draw(Colors.black,x-1,y-1,maxSize+2,15);
        batch.draw(getBackground(),x,y,maxSize,13);
        batch.draw(getForeground(),x,y,currSize,13);
        font.draw(batch,getText(hackmon),x+(maxSize/2),y+12,0,
                Align.center,
                false);
    }
}
