package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.adventure.GridMap;
import com.mygdx.game.adventure.NPC;
import com.mygdx.game.adventure.Player;
import com.mygdx.game.battle.BackPackMenu;
import com.mygdx.game.battle.BattleMap;
import com.mygdx.game.battle.Opponent;
import com.mygdx.game.items.BackPack;
import com.mygdx.game.items.ItemContainer;

public class HackmonsGame extends ApplicationAdapter {
	public static final int SCREENWIDTH=1600;
	public static final int SCREENHEIGHT=900;
	SpriteBatch batch;
	BitmapFont font;
	Texture bg;
	Trainer player, opponent;
	Player testPlayer;
	NPC testNPC;
	BattleMap battleMap;
	BackPackMenu backPackMenu;
	SwitchHackmonMenu switchHackmonMenu;
	public static ScreenState screenState = ScreenState.ADVENTURE;//just
	// change this to get battlemenu again
	public static ScreenState prevScreenState = ScreenState.BATTLEMENU;

	GridMap mapu;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		bg = new Texture(
"core/assets/bg.jpeg"
		);
		mapu = new GridMap("Route1");
		if (RNG.chance(50)) {
			bg = new Texture("core/assets/bg2.png");
		}
		player = new Trainer("Ass Ketchup",
				new Hackmon[]{
					new Hackmon(4, 100),
					new Hackmon(5, 100),
					new Hackmon(6, 98),
					new Hackmon(7, 99),
					new Hackmon(8, 100),
					new Hackmon(9, 100)
				}
			);
		opponent = new Trainer("Titty Mustard",
				new Hackmon[]{
					new Hackmon(9, 100),
					new Hackmon(8, 69),
					new Hackmon(7, 69),
					new Hackmon(6, 69),
					new Hackmon(5, 69),
					new Hackmon(4, 69)
				}
			);
		player.getSelected().receiveExp(15000);
		BackPack backPack = new BackPack();
		backPack.addItem(ItemContainer.ether);
		backPack.addItem(ItemContainer.superEther);
		backPack.addItem(ItemContainer.maxEther);
		backPack.addItem(ItemContainer.potion);
		backPack.addItem(ItemContainer.superPotion);
		backPack.addItem(ItemContainer.maxPotion);
		backPackMenu = new BackPackMenu(backPack,player);
		battleMap = new BattleMap();
		battleMap.setPlayer(player);
		Opponent.setTrainer(opponent);
		battleMap.setOpponent(opponent);
		battleMap.setBg(bg);
		switchHackmonMenu = new SwitchHackmonMenu(player);

		testPlayer = new Player(0,0);
		testNPC = new NPC(800,450);
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
				batch.draw(Colors.green,0,0,1600,900);
				mapu.render(batch);
				testPlayer.render(batch);
				testNPC.render(batch);
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
