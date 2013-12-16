package com.rscar.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GenericObject {
	
	public boolean flaggedForDelete = false;
	protected Sprite sprite;
	protected Vector2 position;
	
	public void update(){};
	
	public void draw(SpriteBatch batch){};
	
	public Vector2 getPosition(){
		return position;
	}
	
	public float getCenterX(){
		return position.x + getWidth()/2;
	}
	
	public float getCenterY(){
		return position.y + getHeight()/2;
	}
	
	public float getWidth(){
		return sprite.getWidth();
	}
	
	public float getHeight(){
		return sprite.getHeight();
	}
	
	public Sprite getSprite() {
		return sprite;
	};

}
