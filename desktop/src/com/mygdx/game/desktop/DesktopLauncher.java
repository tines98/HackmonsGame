package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.HackmonsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Pokemon Johto durududududu";
		cfg.useGL30 = true;
		cfg.height = HackmonsGame.SCREENHEIGHT;
		cfg.width = HackmonsGame.SCREENWIDTH;
		new LwjglApplication(new HackmonsGame(), cfg);
	}
}
