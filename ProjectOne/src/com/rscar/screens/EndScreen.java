package com.rscar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rscar.controllers.GameCameraController;
import com.rscar.objects.FinishScreen;
import com.rscar.projectone.ProjectOneMain;

public class EndScreen extends GenericScreen{
	
	private boolean done = false;
	ProjectOneMain projectOneMain;
	SpriteBatch gameBatch;
	FinishScreen endScreen;
	GameCameraController gameCameraController;
	private BitmapFont font;
	
	public EndScreen(){
		projectOneMain = ProjectOneMain.getInstance();
		gameBatch = new SpriteBatch();
		gameCameraController = new GameCameraController(this);
		loadAssets();
		Gdx.input.setCursorCatched(false);
		endScreen = new FinishScreen(0,0);
		font = new BitmapFont();
	}

	@Override
	public void update(float delta) {

	}

	@Override
	public void draw(float delta) {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);   
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    gameBatch.begin();
		gameBatch.enableBlending();
		
		endScreen.draw(gameBatch);
		font.setColor(0.0f, 0.0f, 0.0f, 1.0f);
		font.draw(gameBatch, Integer.toString(projectOneMain.numDeaths) , 240, 255);
		
		gameBatch.end();
	}

	@Override
	public boolean isDone() {
		return done;
	}
	
	public void loadAssets(){
		projectOneMain.assetManager.setLoader(Texture.class, new TextureLoader(new InternalFileHandleResolver()));
		projectOneMain.assetManager.load("sprites/endScreen.png", Texture.class);

		while(!projectOneMain.assetManager.update()) {
			//display message while loading...lets be real, you have like 5 assets tops
		};
	}

}
