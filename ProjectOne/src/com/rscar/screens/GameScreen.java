package com.rscar.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.rscar.controllers.GameCameraController;
import com.rscar.objects.GenericObject;
import com.rscar.objects.LevelEnd;
import com.rscar.objects.PlacementBlock;
import com.rscar.objects.Player;
import com.rscar.objects.SceneryCeiling;
import com.rscar.objects.SceneryFloor;
import com.rscar.objects.SpikeBlock;
import com.rscar.objects.StaticBlock;
import com.rscar.projectone.ProjectOneMain;

public class GameScreen extends GenericScreen{
	
	private int[][] levelOneArray = 
		{
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,5,5,5,1,1,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,4,4,0,0,0,0,5,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,4,4,4,4,0,0,8,0,0,4,4,4,0,0},
		{0,0,0,1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,4,4,4,4,4,0,0,0,0,0,0,0,5,1,5,5,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,1,1,1,0,0},
		{0,0,0,5,0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,5,0,0,0,0,0,2,0,5,1,1,1,1,1,1,1,0,1,1,1,5,5,0,0},
		{0,0,0,0,0,0,0,0,0,4,1,1,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,5,1,1,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,1,1,1,1,1,1,1,1,5,0,0,0,0},
		{0,0,0,9,0,0,0,0,0,1,1,1,1,0,0,0,0,4,4,4,4,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,5,1,5,5,5,1,1,1,0,0,0,0,0},
		{0,0,4,0,0,0,0,0,0,1,1,1,1,3,3,3,3,1,1,1,1,0,0,0,2,2,0,0,0,4,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,5,5,1,0,0,0,0,0},
		{0,4,1,4,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,3,3,3,3,3,0,0,0,0,0,0,0,2,3,3,3,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0},
		{0,1,1,1,1,1,1,1,1,1,1,1,1,5,5,5,5,5,1,1,1,3,3,3,3,3,3,3,3,1,1,1,1,1,1,5,1,1,0,0,0,0,0,0,0,0,5,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,5,1,1,5,5,5,1,1,1,5,5,5,0,0,0,0,0,5,1,1,1,5,5,1,1,5,5,5,5,1,1,5,5,5,0,0,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,5,5,0,0,0,5,1,1,0,0,0,0,0,0,0,0,0,5,1,5,0,0,1,5,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,5,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		};
	private int[][] levelTwoArray = 
		{
		{0,0,0,0,0,0,0,0,0,4,4,4,0,0,0,0,0,0,0,0,0,0,4,4,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,4,4,4,0,0,0,0,0,1,1,1,0,0,1,3,3,3,3,3,3,3,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,1,1,1,0,0,0,0,0,1,1,5,0,0,5,5,5,5,5,1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,5,1,1,0,9,0,0,0,1,5,0,0,0,0,0,0,0,0,5,5,5,5,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,5,1,0,0,0,0,0,1,4,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,1,1,4,0,0,0,0,0,0,0,0,0,0,0,5,5,5,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,4,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,1,4,4,4,0,0,0,0,0,0,4,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,1,1,4,0,0,0,0,1,1,5,0,3,3,3,0,0,3,3,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,5,1,1,0,0,0,0,1,5,0,3,1,1,1,0,0,1,1,0,0,0,0,0,0,0,5,1,5,5,0,0,0,0,0,0,1,1,1,1,4,4,0,0,0,0,0,0,0,0,0,4,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,1,1,4,4,4,4,1,3,3,1,1,1,1,0,0,1,1,3,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,4,4,4,1,1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,5,1,1,1,1,1,1,1,1,1,1,1,1,0,0,5,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,5,5,5,5,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,5,1,1,1,1,1,1,1,1,1,1,1,0,0,0,5,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,5,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,5,1,1,1,1,1,1,1,1,1,0,0,0,4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,1,1,1,5,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,5,5,5,5,5,5,1,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,1,1,1,1,3,3,3,3,3,1,1,5,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,5,1,1,0,0,0,5,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,5,5,1,1,1,1,1,1,1,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,1,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,3,3,3,3,3,3,3,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,5,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,5,5,5,5,5,5,5,5,5,0,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,2,2,0,0,0,0,2,2,2,0,0,0,0,0,0,0,4,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,1,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			
		};
	private int[][] levelThreeArray = 
		{
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,0,8,0,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,0,4,1,1,1,1,0,0,0,1,1,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,1,1,0,1,1,1,1,1,1,0,1,1,1,1,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,9,0,0,0,0,0,5,1,1,4,4,4,0,0,0,1,1,1,1,1,1,1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,1,1,1,1,4,0,0,5,5,1,1,1,1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,4,4,4,4,4,4,0,0,0,0,0,5,5,1,1,1,0,0,0,0,5,5,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,1,5,0,0,0,0,0,0,0,1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,5,1,1,1,1,5,0,0,0,0,0,0,0,5,0,0,0,0,2,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,5,1,1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,5,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,3,3,1,1,1,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,3,3,3,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,5,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,5,5,5,5,5,1,1,1,1,0,4,4,4,4,4,4,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,1,1,0,1,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,1,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{5,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,5,1,1,1,1,1,5,1,0,5,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,5,1,1,1,5,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,1,5,5,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		
		};
	
	public boolean isDone;
	public int state = 0;
	private int level;
	
	private SpriteBatch gameBatch;
	private ShapeRenderer shapeRenderer;
	private GameCameraController gameCameraController;
	private ProjectOneMain projectOneMain;

	private int[][] levelArray;
	private List<GenericObject> objects = new ArrayList<GenericObject>();
	private List<GenericObject> sceneryObjects = new ArrayList<GenericObject>();
	private Player player;
	
	private boolean rightPressed;
	private boolean leftPressed;
	private boolean spacePressed;
	private Vector2 deltaMouseLocationReal = new Vector2();
	private Vector2 deltaMouseLocation = new Vector2(0,0);
	private Vector2 creatorLine = new Vector2();
	
	private int coolDown = 0;
	private Sprite cursorSprite;
	
	public Sound click;
	public Sound jump;
	public Sound death;
	public Sound endLevel;
	public Music music;
	
	public GameScreen (int level){
		this.level = level;
		projectOneMain = ProjectOneMain.getInstance();
		
		gameBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		loadAssets();
		buildLevel();
		
		gameCameraController = new GameCameraController(this);
		cursorSprite = new Sprite(projectOneMain.assetManager.get("sprites/cursor.png", Texture.class));
		
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);   
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    Gdx.input.setCursorCatched(true);
	}

	@Override
	public void update(float delta) {
		
		//decrement coolDown
		if (coolDown > 0){
			coolDown--;
		}

		//handle input
		rightPressed = false;
		leftPressed = false;
		spacePressed = false;
		if(Gdx.input.isKeyPressed(Keys.A)) leftPressed = true;
		if(Gdx.input.isKeyPressed(Keys.D)) rightPressed = true;
		if(Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isKeyPressed(Keys.W)) spacePressed = true;
		if(Gdx.input.isKeyPressed(Keys.ESCAPE) && Gdx.input.isCursorCatched()) Gdx.input.setCursorCatched(false);
		else if(Gdx.input.isTouched() && !Gdx.input.isCursorCatched()) Gdx.input.setCursorCatched(true);
		
		if(Gdx.input.isTouched() && coolDown == 0){
			coolDown = 8;
			player.createBlock(objects);
		}

		player.setRightPressed(rightPressed);
		player.setLeftPressed(leftPressed);
		player.setSpacePressed(spacePressed);
		
		//gets the mouse location in comparison to the player
		deltaMouseLocationReal.x = Gdx.input.getX() - 256;
		deltaMouseLocationReal.y = -Gdx.input.getY() + 256;
		
		if (deltaMouseLocationReal.dst2(deltaMouseLocation) > 1){
			float xMoveDist = Math.abs(deltaMouseLocationReal.x - deltaMouseLocation.x) / 2;
			float yMoveDist = Math.abs(deltaMouseLocationReal.y - deltaMouseLocation.y) / 2;
			
			if (deltaMouseLocation.x < deltaMouseLocationReal.x) deltaMouseLocation.x += xMoveDist;
			else if (deltaMouseLocation.x > deltaMouseLocationReal.x) deltaMouseLocation.x -= xMoveDist;
			
			if (deltaMouseLocation.y < deltaMouseLocationReal.y) deltaMouseLocation.y += yMoveDist;
			else if (deltaMouseLocation.y > deltaMouseLocationReal.y) deltaMouseLocation.y -= yMoveDist;
		}

		creatorLine.x = player.getPosition().x + deltaMouseLocation.cpy().nor().scl(512).x + player.getWidth()/2;
		creatorLine.y = player.getPosition().y + deltaMouseLocation.cpy().nor().scl(512).y + player.getHeight()/2;
		
		//iterate and call the update method for each of our objects

		player.update(objects, creatorLine);
		for (GenericObject object: objects){
			if (!object.getClass().equals(Player.class)){
				object.update();
			}
		}

		gameCameraController.update();	
		
	}

	@Override
	public void draw(float delta) {

		//clear the screen
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);   
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    
	    //draw sighting line
		Gdx.gl10.glLineWidth(1);
		Gdx.gl.glEnable(GL10.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.setProjectionMatrix(gameCameraController.getCamera().combined);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(0, 0, 0, 0.4f);
		shapeRenderer.line(player.getPosition().x + player.getWidth()/2, player.getPosition().y + player.getHeight()/2, creatorLine.x, creatorLine.y);
	    shapeRenderer.end();
	    Gdx.gl.glDisable(GL10.GL_BLEND);

	    gameBatch.setProjectionMatrix(gameCameraController.getCamera().combined);
		gameBatch.begin();
		gameBatch.enableBlending();
		for (GenericObject sceneryObject: sceneryObjects){
			sceneryObject.draw(gameBatch);
		}
		for (int i = 0; i < objects.size(); i++){
			objects.get(i).draw(gameBatch);
		}
		
		cursorSprite.setPosition(deltaMouseLocation.x + player.getPosition().x + player.getWidth()/2 - cursorSprite.getWidth()/2, deltaMouseLocation.y + player.getPosition().y + player.getHeight()/2 - cursorSprite.getHeight()/2);
		cursorSprite.draw(gameBatch);	
		
		gameBatch.end(); 
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return isDone;
	}

	//translates arrays into the actual levels
	private void buildLevel(){
		if (level == 1) levelArray = levelOneArray;
		if (level == 2) levelArray = levelTwoArray;
		if (level == 3) levelArray = levelThreeArray;

		//iterate through the level objects
		for (int y=0; y<levelArray.length; y++){
			for (int x=0; x<levelArray[y].length; x++){
				
				if (levelArray[levelArray.length-y-1][x]==1){
					objects.add(new StaticBlock(x*32,y*32));
				}
				if (levelArray[levelArray.length-y-1][x]==2){
					objects.add(new PlacementBlock(x*32,y*32));
				}
				if (levelArray[levelArray.length-y-1][x]==3){
					objects.add(new SpikeBlock(x*32,y*32));
				}
				
				if (levelArray[levelArray.length-y-1][x]==4){
					sceneryObjects.add(new SceneryFloor(x*32,y*32));
				}
				if (levelArray[levelArray.length-y-1][x]==5){
					sceneryObjects.add(new SceneryCeiling(x*32,y*32));
				}
				
				if (levelArray[levelArray.length-y-1][x]==8){
					objects.add(new LevelEnd(x*32,y*32));
				}
				
				if (levelArray[levelArray.length-y-1][x]==9){
					player = new Player(x*32,y*32, this);
					objects.add(player);
				}
				
			}
		}
	}
	
	public void loadAssets(){
		projectOneMain.assetManager.setLoader(Texture.class, new TextureLoader(new InternalFileHandleResolver()));
		projectOneMain.assetManager.load("sprites/cursor.png", Texture.class);
		projectOneMain.assetManager.load("sprites/player.png", Texture.class);
		projectOneMain.assetManager.load("sprites/staticBlock.png", Texture.class);
		projectOneMain.assetManager.load("sprites/spikeBlock.png", Texture.class);
		projectOneMain.assetManager.load("sprites/projectionBlock.png", Texture.class);
		projectOneMain.assetManager.load("sprites/createdBlock.png", Texture.class);
		projectOneMain.assetManager.load("sprites/placementBlock.png", Texture.class);
		projectOneMain.assetManager.load("sprites/levelEnd.png", Texture.class);
		
		projectOneMain.assetManager.load("sprites/scenery_floor_1.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_floor_2.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_floor_3.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_floor_4.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_floor_5.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_floor_6.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_floor_7.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_floor_8.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_floor_9.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_floor_10.png", Texture.class);
		
		projectOneMain.assetManager.load("sprites/scenery_ceiling_1.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_ceiling_2.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_ceiling_3.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_ceiling_4.png", Texture.class);
		projectOneMain.assetManager.load("sprites/scenery_ceiling_5.png", Texture.class);
		
		
		click = Gdx.audio.newSound(Gdx.files.internal("data/click.wav"));
		death = Gdx.audio.newSound(Gdx.files.internal("data/death.wav"));
		jump = Gdx.audio.newSound(Gdx.files.internal("data/jump.wav"));
		endLevel = Gdx.audio.newSound(Gdx.files.internal("data/endLevel.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music.wav"));
		
		music.setLooping(true);
		music.setVolume(0.8f);
		music.play();
		while(!projectOneMain.assetManager.update()) {
			//display message while loading...lets be real, you have like 5 assets tops
		};
	}
	
	public Player getPlayer(){
		try{
			return player;
		} catch (Exception e){
			System.out.println("Exception while retrieving player, has it been instantiated?");
			return null;
		}
	}
	public void setCreatorLine(float x, float y){
		creatorLine.x = x;
		creatorLine.y = y;
	}
	public List<GenericObject> getObjects(){
		return objects;
	}
}
