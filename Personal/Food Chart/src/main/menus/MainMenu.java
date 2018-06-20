package main.menus;

import main.database.DatabaseAccess;

public class MainMenu extends Menu{
	
	public MainMenu( DatabaseAccess dbAccess){
		super(dbAccess);
	}
	
	@Override
	public void run(){
		boolean running = true;
		while(running){
			printMenu();
			switch(userChoice(3)){
				case 1: 
					ViewMenu vm = new ViewMenu(dbAccess);
					vm.run();
					break;
				case 2:
					EditMenu em = new EditMenu(dbAccess);
					em.run();
					break;
				case 3:
					running = false;
			}
		}
	}
	
	@Override
	protected void printMenu(){
		System.out.println("=============");
		System.out.println("Food Chart");
		System.out.println("=============");
		System.out.println("1. View Data");
		System.out.println("2. Edit Data");
		System.out.println("3. Quit");
	}
	
}
