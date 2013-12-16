package com.rscar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rscar.controllers.GameCameraController;
import com.rscar.objects.TitleScreen;
import com.rscar.projectone.ProjectOneMain;

public class StartScreen extends GenericScreen{
	
	private boolean done = false;
	ProjectOneMain projectOneMain;
	SpriteBatch gameBatch;
	TitleScreen titleScreen;
	GameCameraController gameCameraController;
	
	public StartScreen(){
		projectOneMain = ProjectOneMain.getInstance();
		gameBatch = new SpriteBatch();
		gameCameraController = new GameCameraController(this);
		loadAssets();
		
		titleScreen = new TitleScreen(0,0);
	}

	@Override
	public void update(float delta) {
		if(Gdx.input.isKeyPressed(Keys.A)) done = true;
		if(Gdx.input.isKeyPressed(Keys.D)) done = true;
		if(Gdx.input.isKeyPressed(Keys.S)) done = true;
		if(Gdx.input.isKeyPressed(Keys.W)) done = true;
		if(Gdx.input.isKeyPressed(Keys.SPACE)) done = true;
		if(Gdx.input.isTouched()) done = true;

		
	}

	@Override
	public void draw(float delta) {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);   
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    gameBatch.begin();
		gameBatch.enableBlending();
		
		titleScreen.draw(gameBatch);
		
		gameBatch.end();
	}

	@Override
	public boolean isDone() {
		return done;
	}
	
	public void loadAssets(){
		projectOneMain.assetManager.setLoader(Texture.class, new TextureLoader(new InternalFileHandleResolver()));
		projectOneMain.assetManager.load("sprites/titleScreen.png", Texture.class);

		while(!projectOneMain.assetManager.update()) {
			//display message while loading...lets be real, you have like 5 assets tops
		};
	}

}
