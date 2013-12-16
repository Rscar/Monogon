package com.rscar.controllers;

import com.rscar.projectone.ProjectOneMain;
import com.rscar.screens.EndScreen;
import com.rscar.screens.GameScreen;
import com.rscar.screens.GenericScreen;
import com.rscar.screens.StartScreen;



public class ScreenController {
	
	public ProjectOneMain projectOneMain;
	public int level = 1;
	
	public ScreenController(){
		projectOneMain = ProjectOneMain.getInstance();
	}

	public void checkScreen(GenericScreen currentScreen){
		
		//if the screen is completed
		if (currentScreen.isDone()) {
			// dispose the resources of the current screen
			currentScreen.dispose();

			if (currentScreen instanceof StartScreen) {
				projectOneMain.setScreen(new GameScreen(level));
			} 
			
			else if(currentScreen instanceof GameScreen) {
				
				if (((GameScreen) currentScreen).state == 1){
					if (level == 3){
						projectOneMain.setScreen(new EndScreen());
					}
					else{
						level++;
						projectOneMain.setScreen(new GameScreen(level));
					}
					
				}
				else if (((GameScreen) currentScreen).state == 2){
					projectOneMain.numDeaths++;
					projectOneMain.setScreen(new GameScreen(level));
				}
				
			}
		}
		
	}

}