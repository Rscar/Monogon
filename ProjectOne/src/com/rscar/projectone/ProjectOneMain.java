package com.rscar.projectone;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.rscar.controllers.ScreenController;
import com.rscar.screens.GenericScreen;
import com.rscar.screens.StartScreen;




public class ProjectOneMain extends Game implements ApplicationListener {

	public GenericScreen currentScreen;
	public ScreenController screenController;
	public AssetManager assetManager;
	public static ProjectOneMain projectOneMain;
	
	public int numDeaths = 0;
	
	@Override
	public void create() {	
		
		projectOneMain = this;

		// Asset manager for loading and controlling assets
		assetManager = new AssetManager();
		
		//controller of the screens, this will check to see if screens need to be progressed or update the current screen
		screenController = new ScreenController();
		
		//initially set the screen to a gamescreen level 1, will later change
		setScreen (new StartScreen());

	}

	@Override
	public void dispose() {
	}

	//this is the main driving game loop
	@Override
	public void render() {	
		
		//checks if current screen is finished, if it is, it will be progressed by the screen controller
		currentScreen = (GenericScreen) getScreen();
		screenController.checkScreen(currentScreen);
		
		//update the current screen
		currentScreen.render(Gdx.graphics.getDeltaTime());
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	public static ProjectOneMain getInstance(){
		return projectOneMain;
	}
}
