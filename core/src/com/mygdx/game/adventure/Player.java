package com.mygdx.game.adventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {
    int x, y;
    Animation<TextureRegion> walkRightAnimation,walkLeftAnimation,
            walkUpAnimation, walkDownAnimation;
    Texture walkSheet;
    float stateTime=0;
    AnimationState animationState;
    TextureRegion currentFrame;


    public Player(int x, int y){
        this.x = x;
        this.y = y;
        loadAnimations();
    }

    private void loadAnimations(){
        animationState = AnimationState.WALK_RIGHT;
        walkSheet = new Texture("core/assets/redWalkSheet.png");
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth()/4,walkSheet.getHeight()/4);
        TextureRegion[] walkFramesDown = new TextureRegion[4];
        TextureRegion[] walkFramesLeft = new TextureRegion[4];
        TextureRegion[] walkFramesRight = new TextureRegion[4];
        TextureRegion[] walkFramesUp = new TextureRegion[4];
		for (int i = 0; i < 4; i++) {
            walkFramesDown[i] = tmp[0][i];
            walkFramesLeft[i] = tmp[1][i];
            walkFramesRight[i] = tmp[2][i];
            walkFramesUp[i] = tmp[3][i];
		}
		walkRightAnimation = new Animation<TextureRegion>(0.1f,
                walkFramesRight);
		walkLeftAnimation = new Animation<TextureRegion>(0.1f,
                walkFramesLeft);
		walkDownAnimation = new Animation<TextureRegion>(0.1f,
                walkFramesDown);
		walkUpAnimation = new Animation<TextureRegion>(0.1f,
                walkFramesUp);
    }

    public void render(SpriteBatch batch){
        if (animationState == AnimationState.STANDING)
            stateTime=0;
        else
            stateTime += Gdx.graphics.getDeltaTime();
        updateSprite();
        batch.draw(currentFrame,x,y,96*4,96*4);
        input();
    }

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
    public void input(){
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            animationState = AnimationState.WALK_RIGHT;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            animationState = AnimationState.WALK_LEFT;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.W)){
            animationState = AnimationState.WALK_UP;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            animationState = AnimationState.WALK_DOWN;
        }
        else {
            animationState = AnimationState.STANDING;
        }
    }
}
