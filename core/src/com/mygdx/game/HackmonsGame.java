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

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		bg = new Texture(
"core/assets/bg.jpeg"
		);
		hackmon1 = new Hackmon(4, 1);
		hackmon2 = new Hackmon(5, 2);
		battleMap = new BattleMap();
		battleMap.setHackmon1(hackmon1);
		battleMap.setHackmon2(hackmon2);
		battleMap.setBg(bg);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
		battleMap.render(batch, font);
		batch.end();
		if (Gdx.input.isKeyPressed(Input.Keys.A)) hackmon1.takeDamage(2);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		bg.dispose();
	}
}
