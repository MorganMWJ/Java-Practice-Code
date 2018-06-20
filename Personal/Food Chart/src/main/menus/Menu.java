package main.menus;

import java.util.Scanner;

import main.database.DatabaseAccess;

public abstract class Menu {
	protected DatabaseAccess dbAccess;
	
	public Menu(DatabaseAccess dbAccess){
		this.dbAccess = dbAccess;
	}
	
	protected int userChoice(int maxInt){
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		
		boolean badInput = true;
		int res = 0;
		while(badInput){
			try{
				System.out.print("Enter choice => ");
				res = Integer.parseInt(reader.nextLine());
				if(res>=1 && res<=maxInt){
					badInput = false;
				}
				else{
					System.err.println("INVALID CHOICE");
				}
			}catch(NumberFormatException e){
				System.err.println("INVALID CHOICE");
			}
		}
		return res;
	}
	
	abstract public void run();
	abstract protected void printMenu();
}
