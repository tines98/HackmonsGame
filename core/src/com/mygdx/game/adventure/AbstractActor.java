package com.mygdx.game.adventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class AbstractActor {
    int x, y;
    Animation<TextureRegion> walkRightAnimation,walkLeftAnimation,
            walkUpAnimation, walkDownAnimation;
    Texture walkSheet;
    float stateTime=0;
    AnimationState animationState;
    TextureRegion currentFrame;


    public AbstractActor(int x, int y){
        this.x = x;
        this.y = y;
    }

    protected abstract Texture getWalkSheet();

    protected abstract void loadAnimations();

    public abstract void render(SpriteBatch batch);

    public void updateSprite(){
        switch (animationState){
            case STANDING:
//                currentFrame = walkRightAnimation.getKeyFrame(0, true);
                break;
            case WALK_LEFT:
                currentFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
                x-=1;
                break;
            case WALK_RIGHT:
                currentFrame = walkRightAnimation.getKeyFrame(stateTime, true);
                x+=1;
                break;
            case WALK_UP:
                currentFrame = walkUpAnimation.getKeyFrame(stateTime,true);
                y+=1;
                break;
            case WALK_DOWN:
                currentFrame = walkDownAnimation.getKeyFrame(stateTime,true);
                y-=1;
                break;
        }
    }
}
