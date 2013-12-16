package com.rscar.utilities;

import com.badlogic.gdx.math.Vector2;

public class Line {
	
	public static enum Side{LEFT, RIGHT, TOP, BOTTOM, NONE};
	
	private Vector2 startPoint;
	private Vector2 endPoint;
	private Side side;
	
	
	
	public Line(float x1, float y1, float x2, float y2){
		startPoint = new Vector2(x1, y1);
		endPoint = new Vector2(x2, y2);
	}
	public Line(){
		startPoint = new Vector2();
		endPoint = new Vector2();
	}
	
	public Vector2 getStartPoint(){
		return startPoint;
	}
	
	public Vector2 getEndPoint(){
		return endPoint;
	}
	
	public void setStartPoint(float x, float y){
		startPoint.x = x;
		startPoint.y = y;
	}
	
	public void setEndPoint(float x, float y){
		endPoint.x = x;
		endPoint.y = y;
	}
	
	public void setSide(Side side){
		this.side = side;
	}
	
	public Side getSide(){
		return side;
	}

}
