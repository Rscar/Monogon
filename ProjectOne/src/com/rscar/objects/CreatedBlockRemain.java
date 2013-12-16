package com.rscar.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.rscar.projectone.ProjectOneMain;
import com.rscar.screens.GameScreen;

public class CreatedBlockRemain extends GenericObject{

	private ProjectOneMain projectOneMain;
	private GameScreen screen;
	private float scale = 1.0f;
	
	public CreatedBlockRemain(Vector2 initPos, GameScreen screen){
		projectOneMain = ProjectOneMain.getInstance();
		this.screen = screen;
		
		position = new Vector2();
		
		position = initPos;
		
		sprite = new Sprite(projectOneMain.assetManager.get("sprites/createdBlock.png", Texture.class));
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(SpriteBatch batch) {
		
		scale -= 0.05f;
		
		if (scale < 0){
			screen.getObjects().remove(this);
		}
		sprite.setPosition(position.x, position.y);
		sprite.setScale(scale);
		sprite.draw(batch);		
	}
}
