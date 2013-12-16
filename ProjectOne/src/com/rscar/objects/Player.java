package com.rscar.objects;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.rscar.projectone.ProjectOneMain;
import com.rscar.screens.GameScreen;
import com.rscar.utilities.Line;
import com.rscar.utilities.Line.Side;
import com.rscar.utilities.MiscUtils;

public class Player extends GenericObject{
	
	private ProjectOneMain projectOneMain;
	private GameScreen screen;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean spacePressed = false;
	private boolean jumpable = false;

	private float gravity = -0.2f;
	private float yVelocity = 0;
	private float xVelocity = 0;
	
	private GenericObject closerObject;
	private GenericObject tempObject = new GenericObject();
	private ProjectionBlock projectionBlock = new ProjectionBlock();
	private CreatedBlock createdBlock = new CreatedBlock();
	private Side createSide;
	private Line line1 = new Line();
	private Line line2 = new Line();
	
	private Vector2 center;
	
	public Player(int x, int y, GameScreen screen){
		projectOneMain = ProjectOneMain.getInstance();
		this.screen = screen;
		screen.getObjects().add(projectionBlock);
		screen.getObjects().add(createdBlock);
		
		position = new Vector2();
		center = new Vector2();

		position.x = x;
		position.y = y;
		
		sprite = new Sprite(projectOneMain.assetManager.get("sprites/player.png", Texture.class));
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/4);
		
		tempObject.position = new Vector2(5000, 5000);
		closerObject = tempObject;
	}

	public void update(List<GenericObject> objects, Vector2 creatorLine) {
		updatePlayerMovement(objects);
		
		//check to see if we fell off the map
		if (getPosition().y < -64){
			screen.isDone = true;
			screen.state = 2;
			screen.death.play(1.0f);
		}

		//check to see if we need to restart the level or go to the next one
		for (GenericObject object: objects){
			if (object.getClass().equals(SpikeBlock.class)){
				if (MiscUtils.isOverlap(this, object)){
					screen.isDone = true;
					screen.state = 2;
					screen.death.play(1.0f);
				}
			}
			if (object.getClass().equals(LevelEnd.class)){
				if (MiscUtils.isOverlap(this, object)){
					screen.isDone = true;
					screen.state = 1;
					screen.endLevel.play(0.6f);
				}
			}
		}
		
		updateBlockCreator(objects, creatorLine);
		
		projectionBlock.position.x = 5000;
		projectionBlock.position.y = 5000;
		
		for (GenericObject object:objects){
			if (object.equals(closerObject)){
				if (object.getClass().equals(StaticBlock.class)){
					if (createSide == Side.RIGHT){
						projectionBlock.position = object.getPosition().cpy();
						projectionBlock.position.x += object.getWidth();
					}
					else if (createSide == Side.LEFT){
						projectionBlock.position = object.getPosition().cpy();
						projectionBlock.position.x -= object.getWidth();
					}
					else if (createSide == Side.TOP){
						projectionBlock.position = object.getPosition().cpy();
						projectionBlock.position.y += object.getHeight();
					}
					else if (createSide == Side.BOTTOM){
						projectionBlock.position = object.getPosition().cpy();
						projectionBlock.position.y -= object.getHeight();
					}
				}
				else if (object.getClass().equals(PlacementBlock.class)){
					projectionBlock.position = object.getPosition().cpy();
				}
			}
		}
	}
	
	//this method should theoretically never be called...the other method should be favored
	@Override
	public void update() {		
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.setPosition(position.x, position.y);
		sprite.draw(batch);		
	}
	
	public void updatePlayerMovement(List<GenericObject> objects){
		xVelocity = 0;
		
		if (rightPressed){
			xVelocity = 3f;
		}
		if (leftPressed){
			xVelocity = -3f;
		}
		if (spacePressed && jumpable && yVelocity == 0){
			yVelocity += 5.5f;
			jumpable = false;
			
			screen.jump.play(1.0f);
		}
		
		//update for gravity
		yVelocity +=gravity;
		
		for(GenericObject object: objects){
			
			//make sure its a block we are checking against
			if(object.getClass().equals(StaticBlock.class) || object.getClass().equals(CreatedBlock.class)){
				//if player is falling
				if (yVelocity < 0){
					//check to see if there is an x overlap
					if (getPosition().x + getWidth()/2 > object.getPosition().x - object.getWidth()/2 && getPosition().x - getWidth()/2 < object.getPosition().x + object.getWidth()/2){
						//check to see if the next frame holds an overlap
						if (getPosition().y - getHeight()/2 + yVelocity < object.getPosition().y + object.getHeight()/2 && getPosition().y - getHeight()/2 + yVelocity > object.getPosition().y - object.getHeight()/2){
							position.y = object.getPosition().y + object.getHeight()/2 + getHeight()/2;
							yVelocity = 0;
							jumpable = true;
						}
					}
				//if player is jumping
				} else if (yVelocity > 0){
					//check to see if there is an x overlap
					if (getPosition().x + getWidth()/2 > object.getPosition().x - object.getWidth()/2 && getPosition().x - getWidth()/2 < object.getPosition().x + object.getWidth()/2){
						//check to see if the next frame holds an overlap
						if (getPosition().y + getHeight()/2 + yVelocity < object.getPosition().y + object.getHeight()/2 && getPosition().y + getHeight()/2 + yVelocity > object.getPosition().y - object.getHeight()/2){
							position.y = object.getPosition().y - object.getHeight()/2 - getHeight()/2;
							yVelocity = 0;
						}
					}
				}
				
				//if player is attempting to move right
				if (xVelocity > 0){
					//check to see if there is an y overlap
					if (getPosition().y + getHeight()/2 > object.getPosition().y - object.getHeight()/2 && getPosition().y - getHeight()/2 < object.getPosition().y + object.getHeight()/2){
						//check to see if the next frame holds an overlap
						if (getPosition().x + getWidth()/2 + xVelocity < object.getPosition().x + object.getWidth()/2 && getPosition().x + getWidth()/2 + xVelocity > object.getPosition().x - object.getWidth()/2){
							position.x = object.getPosition().x - object.getWidth()/2 - getWidth()/2;
							xVelocity = 0;
						}
					}
				//if player is attempting to move left
				} else if (xVelocity < 0){
					//check to see if there is an y overlap
					if (getPosition().y + getHeight()/2 > object.getPosition().y - object.getHeight()/2 && getPosition().y - getHeight()/2 < object.getPosition().y + object.getHeight()/2){
						//check to see if the next frame holds an overlap
						if (getPosition().x - getWidth()/2 + xVelocity < object.getPosition().x + object.getWidth()/2 && getPosition().x - getWidth()/2 + xVelocity > object.getPosition().x - object.getWidth()/2){
							position.x = object.getPosition().x + object.getWidth()/2 + getWidth()/2;
							xVelocity = 0;
						}
					}
				}
			}
			
		}
		position.y += yVelocity;
		position.x += xVelocity;
		
		center.x = position.x + getWidth()/2;
		center.y = position.y + getHeight()/2;
		
	}
	
	public void updateBlockCreator(List<GenericObject> objects, Vector2 creatorLine){
		
		closerObject = tempObject;
		for(GenericObject object: objects){
			
			if (object.getClass().equals(StaticBlock.class) || object.getClass().equals(PlacementBlock.class)){
				//first we will construct our two lines to test intersections with
				//there are 8 total scenarios for this test
				//perfectly centered along one side (4)
				//or two sides visible (4)
				//player is perfectly to one of the sides
				if (getCenterY() <= object.getCenterY() + object.getHeight()/2 && getCenterY() > object.getCenterY() - object.getHeight()/2){
					//player is perfectly on the left side
					if (getCenterX() <= object.getCenterX()){
						line1.setStartPoint(object.getCenterX() - object.getWidth()/2, object.getCenterY() - object.getHeight()/2);
						line1.setEndPoint(object.getCenterX() - object.getWidth()/2, object.getCenterY() + object.getHeight()/2);
						line1.setSide(Side.LEFT);
						
						line2.setStartPoint(0,0);
						line2.setEndPoint(0,0);
						line2.setSide(Side.NONE);
						
						checkLines(object, creatorLine);
					}
					//player is perfectly on the right side
					else if (getCenterX() > object.getCenterX()){
						line1.setStartPoint(object.getCenterX() + object.getWidth()/2, object.getCenterY() + object.getHeight()/2);
						line1.setEndPoint(object.getCenterX() + object.getWidth()/2, object.getCenterY() - object.getHeight()/2);
						line1.setSide(Side.RIGHT);
						
						line2.setStartPoint(0,0);
						line2.setEndPoint(0,0);
						line2.setSide(Side.NONE);
						
						checkLines(object, creatorLine);
					}
				}
				//player is perfectly above or below
				else if (getCenterX() <= object.getCenterX() + object.getWidth()/2 && getCenterX() > object.getCenterX() - object.getWidth()/2){
					//object is perfectly above
					if (getCenterY() > object.getCenterY()){
						line1.setStartPoint(0,0);
						line1.setEndPoint(0,0);
						line1.setSide(Side.NONE);
						
						line2.setStartPoint(object.getCenterX() - object.getWidth()/2, object.getCenterY() + object.getHeight()/2);
						line2.setEndPoint(object.getCenterX() + object.getWidth()/2, object.getCenterY() + object.getHeight()/2);
						line2.setSide(Side.TOP);
						
						checkLines(object, creatorLine);
					}
					//object is perfectly below
					else if (getCenterY() <= object.getCenterY()){
						line1.setStartPoint(0,0);
						line1.setEndPoint(0,0);
						line1.setSide(Side.NONE);
						
						line2.setStartPoint(object.getCenterX() + object.getWidth()/2, object.getCenterY() - object.getHeight()/2);
						line2.setEndPoint(object.getCenterX() - object.getWidth()/2, object.getCenterY() - object.getHeight()/2);
						line2.setSide(Side.BOTTOM);

						checkLines(object, creatorLine);
					}
				}
				
				//player is facing a corner
				else if (getCenterX() <= object.getCenterX()){
					//top left corner
					if (getCenterY() > object.getCenterY()){
						line1.setStartPoint(object.getCenterX() - object.getWidth()/2, object.getCenterY() - object.getHeight()/2);
						line1.setEndPoint(object.getCenterX() - object.getWidth()/2, object.getCenterY() + object.getHeight()/2);
						line1.setSide(Side.LEFT);
						
						line2.setStartPoint(object.getCenterX() - object.getWidth()/2, object.getCenterY() + object.getHeight()/2);
						line2.setEndPoint(object.getCenterX() + object.getWidth()/2, object.getCenterY() + object.getHeight()/2);
						line2.setSide(Side.TOP);
						
						checkLines(object, creatorLine);
					}
					//bottom left corner
					else if (getCenterY() <= object.getCenterY()){
						line1.setStartPoint(object.getCenterX() - object.getWidth()/2, object.getCenterY() - object.getHeight()/2);
						line1.setEndPoint(object.getCenterX() - object.getWidth()/2, object.getCenterY() + object.getHeight()/2);
						line1.setSide(Side.LEFT);
						
						line2.setStartPoint(object.getCenterX() + object.getWidth()/2, object.getCenterY() - object.getHeight()/2);
						line2.setEndPoint(object.getCenterX() - object.getWidth()/2, object.getCenterY() - object.getHeight()/2);
						line2.setSide(Side.BOTTOM);

						checkLines(object, creatorLine);
					}
					
				}
				else if (getCenterX() > object.getCenterX()){
					//top right corner
					if (getCenterY() > object.getCenterY()){
						line1.setStartPoint(object.getCenterX() + object.getWidth()/2, object.getCenterY() + object.getHeight()/2);
						line1.setEndPoint(object.getCenterX() + object.getWidth()/2, object.getCenterY() - object.getHeight()/2);
						line1.setSide(Side.RIGHT);
						
						line2.setStartPoint(object.getCenterX() - object.getWidth()/2, object.getCenterY() + object.getHeight()/2);
						line2.setEndPoint(object.getCenterX() + object.getWidth()/2, object.getCenterY() + object.getHeight()/2);
						line2.setSide(Side.TOP);

						checkLines(object, creatorLine);
					}
					//bottom right corner
					else if (getCenterY() <= object.getCenterY()){
						line1.setStartPoint(object.getCenterX() + object.getWidth()/2, object.getCenterY() + object.getHeight()/2);
						line1.setEndPoint(object.getCenterX() + object.getWidth()/2, object.getCenterY() - object.getHeight()/2);
						line1.setSide(Side.RIGHT);
						
						line2.setStartPoint(object.getCenterX() + object.getWidth()/2, object.getCenterY() - object.getHeight()/2);
						line2.setEndPoint(object.getCenterX() - object.getWidth()/2, object.getCenterY() - object.getHeight()/2);
						line2.setSide(Side.BOTTOM);
						
						checkLines(object, creatorLine);
					}
				}
			}
		}
	}
	
	public void checkLines(GenericObject object, Vector2 creatorLine){
		if (line1.getSide() == Side.LEFT){
			if (line1.getStartPoint().x <= creatorLine.x && line1.getStartPoint().x > getPosition().x){
				float slope = (creatorLine.y - getCenterY()) / (creatorLine.x - getCenterX());
				float xx = line1.getStartPoint().x - getCenterX();
				if ((slope) * (xx) + getCenterY() > line1.getStartPoint().y && (slope) * (xx) + getCenterY() <= line1.getEndPoint().y){
					if (object.getPosition().dst2(getPosition()) <= closerObject.getPosition().dst2(getPosition())){

						closerObject = object;
						createSide = Side.LEFT;
						screen.setCreatorLine(line1.getStartPoint().x, (slope) * (xx) + getCenterY());
					}
				}
			}
		}
		else if (line1.getSide() == Side.RIGHT){
			if (line1.getStartPoint().x > creatorLine.x && line1.getStartPoint().x <= getPosition().x){
				float slope = (creatorLine.y - getCenterY()) / (creatorLine.x - getCenterX());
				float xx = line1.getStartPoint().x - getCenterX();
				
				if ((slope) * (xx) + getCenterY() <= line1.getStartPoint().y && (slope) * (xx) + getCenterY() > line1.getEndPoint().y){
					if (object.getPosition().dst2(getPosition()) <= closerObject.getPosition().dst2(getPosition())){

						closerObject = object;
						createSide = Side.RIGHT;
						screen.setCreatorLine(line1.getStartPoint().x, (slope) * (xx) + getCenterY());
					}
				}
			}
		}
		if (line2.getSide() == Side.BOTTOM){

			if (line2.getStartPoint().y <= creatorLine.y && line2.getStartPoint().y > getPosition().y){
				float slope = (creatorLine.x - getCenterX()) / (creatorLine.y - getCenterY());
				float yy = line2.getStartPoint().y - getCenterY();
				
				if ((slope) * (yy) + getCenterX() <= line2.getStartPoint().x && (slope) * (yy) + getCenterX() > line2.getEndPoint().x){
					if (object.getPosition().dst2(getPosition()) <= closerObject.getPosition().dst2(getPosition())){

						closerObject = object;
						createSide = Side.BOTTOM;
						screen.setCreatorLine((slope) * (yy) + getCenterX(), line2.getStartPoint().y);
					}
				}
			}
		}
		else if (line2.getSide() == Side.TOP){
			if (line2.getStartPoint().y > creatorLine.y && line2.getStartPoint().y <= getPosition().y){
				float slope = (creatorLine.x - getCenterX()) / (creatorLine.y - getCenterY());
				float yy = line2.getStartPoint().y - getCenterY();
				
				if ((slope) * (yy) + getCenterX() > line2.getStartPoint().x && (slope) * (yy) + getCenterX() <= line2.getEndPoint().x){
					if (object.getPosition().dst2(getPosition()) <= closerObject.getPosition().dst2(getPosition())){

						closerObject = object;
						createSide = Side.TOP;
						screen.setCreatorLine((slope) * (yy) + getCenterX(), line2.getStartPoint().y);
					}
				}
			}
		}
	}
	
	public void createBlock(List<GenericObject> objects){
		if (projectionBlock.getPosition().x != 5000 && projectionBlock.getPosition().y != 5000 && !MiscUtils.isOverlap(this, projectionBlock) && !MiscUtils.isOverlap(createdBlock, projectionBlock)){
			for(GenericObject object:objects){
				if (object.getClass().equals(SpikeBlock.class)){
					if(MiscUtils.isOverlap(object, projectionBlock)){
						return;
					}
				}
			}
			screen.getObjects().add(new CreatedBlockRemain(createdBlock.getPosition().cpy(), screen));
			createdBlock.position = projectionBlock.position.cpy();
			screen.click.play(1.0f);
		}
	}
	
	public void setLeftPressed(boolean pressed){
		leftPressed = pressed;
	}
	
	public void setRightPressed(boolean pressed){
		rightPressed = pressed;
	}
	
	public void setSpacePressed(boolean pressed){
		spacePressed = pressed;
	}
	
}
