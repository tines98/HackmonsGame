package com.mygdx.game.adventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.HackmonsGame;
import com.mygdx.game.RNG;

public class NPC extends AbstractActor{
    public NPC(int x, int y) {
        super(x, y);
        loadAnimations();
        animationState = AnimationState.WALK_DOWN;
        startMove();
    }

    @Override
    protected void loadAnimations(){
        animationState = AnimationState.WALK_RIGHT;
        walkSheet = getWalkSheet();
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth()/12,walkSheet.getHeight()/8);
        TextureRegion[] walkFramesDown = new TextureRegion[24];
        TextureRegion[] walkFramesLeft = new TextureRegion[24];
        TextureRegion[] walkFramesRight = new TextureRegion[24];
        TextureRegion[] walkFramesUp = new TextureRegion[24];
        System.out.println(walkFramesUp.length);
		for (int i = 0; i < 12; i++) {
            walkFramesUp[i] = tmp[0][i];
            walkFramesRight[i] = tmp[1][i];
            walkFramesDown[i] = tmp[2][i];
            walkFramesLeft[i] = tmp[3][i];
		}
		for (int i = 12; i < 24; i++) {
            walkFramesUp[i] = tmp[4][i-12];
            walkFramesRight[i] = tmp[5][i-12];
            walkFramesDown[i] = tmp[6][i-12];
            walkFramesLeft[i] = tmp[7][i-12];
		}
		walkRightAnimation = new Animation<TextureRegion>(0.033f,
                walkFramesRight);
		walkLeftAnimation = new Animation<TextureRegion>(0.033f,
                walkFramesLeft);
		walkDownAnimation = new Animation<TextureRegion>(0.033f,
                walkFramesDown);
		walkUpAnimation = new Animation<TextureRegion>(0.033f,
                walkFramesUp);
		currentFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
    }

    @Override
    protected Texture getWalkSheet() {
        return new Texture("core/assets/linkWalkSheet.png");
    }

    public void render(SpriteBatch batch){
        if (animationState == AnimationState.STANDING)
            stateTime=0;
        else
            stateTime += Gdx.graphics.getDeltaTime();
        updateSprite();
        updatePosition();
        batch.draw(currentFrame,worldX*Settings.TILE_SIZE,worldY*Settings.TILE_SIZE,
                Settings.TILE_SIZE,Settings.TILE_SIZE);
        walk();
    }

    private void walk(){
        if (animationState == AnimationState.STANDING) {
//            if (y == 0) {
//                animationState = AnimationState.WALK_UP;
//                startMove();
//            }
//            else if (y == HackmonsGame.SCREENHEIGHT - 96) {
//                animationState = AnimationState.WALK_DOWN;
//                startMove();
//            }
//            else if (RNG.chance(50)){
//                animationState = AnimationState.WALK_DOWN;
//            }
//            else{
//                animationState = AnimationState.WALK_UP;
//            }
            animationState = AnimationState.WALK_DOWN;
            startMove();
        }
    }
}
