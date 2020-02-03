package com.mygdx.game.adventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {
    int x, y;
    Animation<TextureRegion> walkRightAnimation,walkLeftAnimation;
    Texture walkSheetLeft,walkSheetRight;
    float stateTime=0;
    AnimationState animationState;
    TextureRegion currentFrame;


    public Player(int x, int y){
        this.x = x;
        this.y = y;
        animationState = AnimationState.WALK_RIGHT;
        walkSheetRight = new Texture("core/assets/walkSheet.png");
        walkSheetLeft = new Texture("core/assets/walkSheetLeft.png");
        TextureRegion[][] tmp = TextureRegion.split(walkSheetRight,
                walkSheetRight.getWidth()/4,walkSheetRight.getHeight()/2);
        TextureRegion[][] tmp2 = TextureRegion.split(walkSheetLeft,
                walkSheetLeft.getWidth()/4,walkSheetLeft.getHeight()/2);
        TextureRegion[] walkFramesLeft = new TextureRegion[8];
        TextureRegion[] walkFramesRight = new TextureRegion[8];
		int index = 0;
		for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                walkFramesRight[index] = tmp[i][j];
                walkFramesLeft[index] = tmp2[i][j];
                index++;
            }
		}
		walkRightAnimation = new Animation<TextureRegion>(0.0375f,
                walkFramesRight);
		walkLeftAnimation = new Animation<TextureRegion>(0.0375f,
                walkFramesLeft);
    }

    public void render(SpriteBatch batch){
        if (animationState == AnimationState.STANDING);
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
                x-=-1;
                break;
            case WALK_RIGHT:
                currentFrame = walkRightAnimation.getKeyFrame(stateTime, true);
                x+=-1;
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
        else {
            animationState = AnimationState.STANDING;
        }
    }
}
