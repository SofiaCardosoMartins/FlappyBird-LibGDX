package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.FlappyGameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		/*** INITIAL CONFIGURATIONS ***/
		config.width = FlappyGameClass.WIDTH;
		config.height = FlappyGameClass.HEIGHT;
		config.title = FlappyGameClass.TITLE;

		new LwjglApplication(new FlappyGameClass(), config);
	}
}
