package com.rscar.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.rscar.projectone.ProjectOneMain;

public class CreatedBlock extends GenericObject{

	private ProjectOneMain projectOneMain;
	
	public CreatedBlock(){
		projectOneMain = ProjectOneMain.getInstance();
		
		position = new Vector2();
		
		position.x = 5000;
		position.y = 5000;
		
		sprite = new Sprite(projectOneMain.assetManager.get("sprites/createdBlock.png", Texture.class));
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
