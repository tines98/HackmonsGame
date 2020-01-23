package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.battle.BackPackMenu;
import com.mygdx.game.battle.BattleMap;
import com.mygdx.game.battle.Opponent;

public class HackmonsGame extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Texture bg;
	Trainer player, opponent;
	Hackmon hackmon1;
	Hackmon hackmon2;
	BattleMap battleMap;
	BackPackMenu backPackMenu;
	SwitchHackmonMenu switchHackmonMenu;
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
		player = new Trainer("Ass Ketchup",new Hackmon[]{new Hackmon(4, 100)
		,new Hackmon(4, 69),new Hackmon(6, 50),new Hackmon(5, 10),
				new Hackmon(4, 100),new Hackmon(4, 100)});
		opponent = new Trainer("Ass Ketchup",new Hackmon[]{new Hackmon(4, 100)
		,new Hackmon(4, 69),new Hackmon(4, 50),new Hackmon(5, 10),
				new Hackmon(4, 100),new Hackmon(4, 100)});
		BackPack backPack = new BackPack(5);
		backPack.addItem(new Potion("Uh Oh",10));
		backPack.addItem(new Potion("Stinky",20));
		backPack.addItem(new Potion("Poop",15));
		backPack.addItem(new Potion("Alalalala",60));
		backPack.addItem(new Potion("Funny poop",33));
		backPackMenu = new BackPackMenu(backPack);

		battleMap = new BattleMap();
		battleMap.setPlayer(player);
		//TODO Fix this shit
		Opponent.setTrainer(opponent);
		battleMap.setOpponent(opponent);
		battleMap.setBg(bg);
		switchHackmonMenu = new SwitchHackmonMenu(player);
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
				break;
			case SWITCHMENU:
				switchHackmonMenu.render(batch,font);
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
