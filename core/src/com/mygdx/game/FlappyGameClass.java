package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class FlappyGameClass extends ApplicationAdapter {

	/*** INITIAL CONFIGURATIONS ***/

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Flappy Bird";
	private Music music;

	/*** MANAGE GAME***/

	private GameStateManager gsm;
	private SpriteBatch batch;	//there should only be one instance of a SpriteBatch per game

	@Override
	public void create () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm = new GameStateManager();
		batch = new SpriteBatch();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		MenuState ms = new MenuState(gsm);
		gsm.push(ms);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	//wipes out the screen so that the SpriteBatch can redraw everything
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		music.dispose();
	}
}
