package com.mygdx.game.adventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;

public abstract class AbstractActor {
    int x, y;
    float worldX, worldY;
    float srcX, srcY, destX, destY;
    Animation<TextureRegion> walkRightAnimation,walkLeftAnimation,
            walkUpAnimation, walkDownAnimation;
    Texture walkSheet;
    float stateTime=0;
    AnimationState animationState;
    TextureRegion currentFrame;
    float animTimer=0f;
    final float ANIM_TIME=0.4f;


    public AbstractActor(int x, int y){
        this.x = x;
        this.y = y;
        srcX=x;
        srcY=y;
        destX=x;
        destY=y;
        worldX=x;
        worldY=y;
    }

    protected abstract Texture getWalkSheet();

    protected abstract void loadAnimations();

    public abstract void render(SpriteBatch batch);

    protected void updatePosition(){
        if (animationState==AnimationState.STANDING) return;
        animTimer+=Gdx.graphics.getDeltaTime();
        worldX = Interpolation.pow2.apply(srcX,destX,animTimer/ANIM_TIME);
        worldY = Interpolation.pow2.apply(srcY,destY,animTimer/ANIM_TIME);
        if (animTimer>ANIM_TIME){
            animationState = AnimationState.STANDING;
            animTimer=0f;
        }
    }

    protected void startMove(){
        int dx=0, dy=0,speed=1;
        switch (animationState){
            case STANDING:
                return;
            case WALK_LEFT:
                dx=-speed;
                break;
            case WALK_RIGHT:
                dx=speed;
                break;
            case WALK_UP:
                dy=speed;
                break;
            case WALK_DOWN:
                dy=-speed;
                break;
        }
        animTimer=0f;
        worldX=x;
        worldY=y;
        srcX=x;
        srcY=y;
        x+=dx;
        y+=dy;
        destX=x;
        destY=y;

    }

    public void updateSprite(){
        switch (animationState){
            case STANDING:
//                currentFrame = walkRightAnimation.getKeyFrame(0, true);
                break;
            case WALK_LEFT:
                currentFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
                break;
            case WALK_RIGHT:
                currentFrame = walkRightAnimation.getKeyFrame(stateTime, true);
                break;
            case WALK_UP:
                currentFrame = walkUpAnimation.getKeyFrame(stateTime,true);
                break;
            case WALK_DOWN:
                currentFrame = walkDownAnimation.getKeyFrame(stateTime,true);
                break;
        }
    }
}
