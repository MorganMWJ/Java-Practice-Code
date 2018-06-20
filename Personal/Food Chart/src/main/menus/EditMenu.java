package main.menus;

import java.util.Scanner;

import main.Date;
import main.Meal;
import main.Person;
import main.database.DatabaseAccess;
import main.exceptions.InvalidDateException;
import main.exceptions.InvalidPersonException;

public class EditMenu extends Menu{

	public EditMenu(DatabaseAccess dbAccess) {
		super(dbAccess);
	}

	@Override
	public void run() {
		boolean running = true;
		while(running){
			printMenu();
			switch(userChoice(3)){
				case 1: 
					addMeal();
					break;
					
				case 2:
					removeMeal();
					break;
					
				case 3:
					running = false;
			}
		}
	}

	@Override
	protected void printMenu() {
		System.out.println("=============");
		System.out.println("Edit Menu");
		System.out.println("=============");
		System.out.println("1. Add Meal");
		System.out.println("2. Remove Meal");
		System.out.println("3. Exit");
	}
	
	private void addMeal() {
		System.out.println("Add a meal");
		System.out.println("===========================================");
		
		try {
			@SuppressWarnings("resource")
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter the meal date: ");
			Date date = Date.parseDate(reader.nextLine());
			System.out.println("Enter the meal description: ");
			String desc = reader.nextLine();
			System.out.println("Enter the name of person cooking: ");
			Person person = Person.parsePerson(reader.nextLine());
			dbAccess.addMeal(new Meal(date, person, desc));
			
		} catch(InvalidDateException e){
			System.out.println("ERROR: Invalid person entered");
		} catch(InvalidPersonException e){
			System.out.println("ERROR: Invalid date entered");
		} 
		
		System.out.println("===========================================");
	}

	private void removeMeal() {
		
		System.out.println("Meal Removal");
		System.out.println("===========================================");

		try {
			//get the date of the meal to remove
			@SuppressWarnings("resource")
			Scanner reader = new Scanner(System.in);
			System.out.println("Please enter the date of the meal to remove: ");
			Date removeDate = Date.parseDate(reader.nextLine());
			//remove meal
			dbAccess.removeMeal(removeDate);
			//alert user
			System.out.println("Meal Removed!");
			
		} catch (InvalidDateException e) {
			System.out.println("ERROR: Invalid date entered");
		}

		System.out.println("===========================================");
	}
}
