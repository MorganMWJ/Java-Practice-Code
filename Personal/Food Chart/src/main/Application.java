package main;

import main.database.DatabaseAccess;
import main.menus.MainMenu;

public class Application {
	
	public static void main(String[] args) {		
		//read meals from database
		DatabaseAccess dbAccess = new DatabaseAccess();
		
		//pass meals to mainMenu
		MainMenu mainMenu = new MainMenu(dbAccess);
		
		//start the main menu running
		mainMenu.run();
	}

}
