package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HackmonsGame extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Texture bg;
	Hackmon hackmon1;
	Hackmon hackmon2;
	BattleMap battleMap;
	BackPackMenu backPackMenu;
	public static ScreenState screenState = ScreenState.BATTLEMENU;//just
	// change this to get battlemenu again
	public static ScreenState prevScreenState = ScreenState.BATTLEMENU;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		bg = new Texture(
"core/assets/bg.jpeg"
		);
		hackmon1 = new Hackmon(4, 100);
		hackmon2 = new Hackmon(5, 2);
		BackPack backPack = new BackPack(5);
		backPack.addItem(new Potion("potion",10));
		backPack.addItem(new Potion("potion2",20));
		backPack.addItem(new Potion("potion3",15));
		backPack.addItem(new Potion("potion4",60));
		backPack.addItem(new Potion("potion5",33));
		backPackMenu = new BackPackMenu(backPack);

		battleMap = new BattleMap();
		battleMap.setHackmon1(hackmon1);
		battleMap.setHackmon2(hackmon2);
		battleMap.setBg(bg);
	}

	public static void changeScreenState(ScreenState state){
		prevScreenState = screenState;
		screenState = state;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
		switch (screenState){
			case BAGMENU:
				backPackMenu.render(batch,font);
				break;
			case ADVENTURE:
				break;
			case BATTLEMENU:
				battleMap.render(batch, font);
				if (Gdx.input.isKeyPressed(Input.Keys.H)) hackmon1.takeDamage(2);
				break;
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		bg.dispose();
	}
}
