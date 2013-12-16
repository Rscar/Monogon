package com.rscar.utilities;

import com.badlogic.gdx.math.Vector2;
import com.rscar.objects.GenericObject;

public class MiscUtils {
	
	public static boolean isOverlap(GenericObject object1, GenericObject object2){
		
		if (object1.getPosition().x < object2.getPosition().x + object2.getWidth() && object1.getPosition().x + object1.getWidth() > object2.getPosition().x &&
				object1.getPosition().y < object2.getPosition().y + object2.getHeight() && object1.getPosition().y + object1.getHeight() > object2.getPosition().y){
			return true;
		}
		return false;
	}
	
	public static boolean isOverlap(Vector2 point, GenericObject object){
		if (point.x > object.getPosition().x && point.x < object.getPosition().x + object.getWidth() &&
				point.y > object.getPosition().y && point.y < object.getPosition().y + object.getHeight()){
			return true;
		}
		return false;
	}

}
