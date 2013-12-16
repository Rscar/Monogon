package com.rscar.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.rscar.projectone.ProjectOneMain;

public class SceneryFloor extends GenericObject{

	private ProjectOneMain projectOneMain;
	
	public SceneryFloor(int x, int y){
		projectOneMain = ProjectOneMain.getInstance();
		
		position = new Vector2();
		
		position.x = x;
		position.y = y;
		
		double randNum;
		randNum = Math.random();
		
		if (randNum >= 0 && randNum <= 0.1f){
			sprite = new Sprite(projectOneMain.assetManager.get("sprites/scenery_floor_1.png", Texture.class));
		} else if (randNum > 0.1f && randNum <= 0.2f){
			sprite = new Sprite(projectOneMain.assetManager.get("sprites/scenery_floor_2.png", Texture.class));
		} else if (randNum > 0.2f && randNum <= 0.3f){
			sprite = new Sprite(projectOneMain.assetManager.get("sprites/scenery_floor_3.png", Texture.class));
		} else if (randNum > 0.3f && randNum <= 0.4f){
			sprite = new Sprite(projectOneMain.assetManager.get("sprites/scenery_floor_4.png", Texture.class));
		} else if (randNum > 0.4f && randNum <= 0.5f){
			sprite = new Sprite(projectOneMain.assetManager.get("sprites/scenery_floor_5.png", Texture.class));
		} else if (randNum > 0.5f && randNum <= 0.6f){
			sprite = new Sprite(projectOneMain.assetManager.get("sprites/scenery_floor_6.png", Texture.class));
		} else if (randNum > 0.6f && randNum <= 0.7f){
			sprite = new Sprite(projectOneMain.assetManager.get("sprites/scenery_floor_7.png", Texture.class));
		} else if (randNum > 0.7f && randNum <= 0.8f){
			sprite = new Sprite(projectOneMain.assetManager.get("sprites/scenery_floor_8.png", Texture.class));
		} else if (randNum > 0.8f && randNum <= 0.9f){
			sprite = new Sprite(projectOneMain.assetManager.get("sprites/scenery_floor_9.png", Texture.class));
		} else if (randNum > 0.9f && randNum <= 1){
			sprite = new Sprite(projectOneMain.assetManager.get("sprites/scenery_floor_10.png", Texture.class));
		}

		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.setPosition(position.x, position.y);
		sprite.draw(batch);		
	}
}
