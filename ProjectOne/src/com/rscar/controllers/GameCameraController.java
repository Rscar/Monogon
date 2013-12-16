package com.rscar.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.rscar.screens.EndScreen;
import com.rscar.screens.GameScreen;
import com.rscar.screens.StartScreen;

public class GameCameraController {
	private OrthographicCamera camera;
	private GameScreen screen;
	private StartScreen startScreen;
	private EndScreen endScreen;
	
	public GameCameraController(GameScreen screen){
		
		this.screen = screen;
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(512, h/w * 512);
		
		camera.zoom = 1.0f;
		camera.update();
		


	}
	
	public GameCameraController(StartScreen screen) {
		this.startScreen = screen;
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(512, h/w * 512);
		
		camera.zoom = 1.0f;
		camera.update();
	}
	
	public GameCameraController(EndScreen screen) {
		this.endScreen = screen;
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(512, h/w * 512);
		
		camera.zoom = 1.0f;
		camera.update();
	}

	public void update(){
		
		//follow the players x  and y coordinate
		camera.position.x = screen.getPlayer().getPosition().x + screen.getPlayer().getWidth()/2;
		camera.position.y = screen.getPlayer().getPosition().y + screen.getPlayer().getHeight()/2;
		camera.zoom = 1.0f;

		
		//need to call update after changing camera zoom and position
		camera.update();
		
	}
	
	public OrthographicCamera getCamera(){
		return camera;
	}
}
