package main.menus;

import java.util.ArrayList;

import main.Meal;
import main.Person;
import main.database.DatabaseAccess;

public class ViewMenu extends Menu{

	public ViewMenu(DatabaseAccess dbAccess) {
		super(dbAccess);
	}

	@Override
	public void run() {
		boolean running = true;
		while(running){
			printMenu();
			switch(userChoice(6)){
				case 1: 
					printMorgansMeals();
					break;
					
				case 2:
					printPatricksMeals();
					break;
					
				case 3: 
					printBensMeals();
					break;
					
				case 4:
					printWillsMeals();
					break;
					
				case 5:
					printAllMeals();
					break;
					
				case 6:
					running = false;
			}
		}
	}

	@Override
	protected void printMenu() {
		System.out.println("=============");
		System.out.println("View Menu");
		System.out.println("=============");
		System.out.println("1. " + Person.MORGAN.getName() + "'s  meals - " +
				dbAccess.getPersonMealCount(Person.MORGAN));
		System.out.println("2. " + Person.PATRICK.getName() + "'s meals - " +
				dbAccess.getPersonMealCount(Person.PATRICK));
		System.out.println("3. " + Person.BEN.getName() + "'s     meals - " +
				dbAccess.getPersonMealCount(Person.BEN));
		System.out.println("4. " + Person.WILL.getName() + "'s    meals - " +
				dbAccess.getPersonMealCount(Person.WILL));
		System.out.println("5. All       meals - " + dbAccess.getAllMealCount());
		System.out.println("6. Exit");
	}
	
	private void printMorgansMeals() {
		System.out.println(Person.MORGAN.getName() + "'s meals - " +
				dbAccess.getPersonMealCount(Person.MORGAN));
		System.out.println("===========================================");
		System.out.println(mealsToString(dbAccess.getPersonMeals(Person.MORGAN)));
		System.out.println("===========================================");
	}

	private void printPatricksMeals() {
		System.out.println(Person.PATRICK.getName() + "'s meals - " +
				dbAccess.getPersonMealCount(Person.PATRICK));
		System.out.println("===========================================");
		System.out.println(mealsToString(dbAccess.getPersonMeals(Person.PATRICK)));
		System.out.println("===========================================");
	}

	private void printBensMeals() {
		System.out.println(Person.BEN.getName() + "'s meals - " +
				dbAccess.getPersonMealCount(Person.BEN));
		System.out.println("===========================================");
		System.out.println(mealsToString(dbAccess.getPersonMeals(Person.BEN)));
		System.out.println("===========================================");
	}

	private void printWillsMeals() {
		System.out.println(Person.WILL.getName() + "'s meals - " +
				dbAccess.getPersonMealCount(Person.WILL));
		System.out.println("===========================================");
		System.out.println(mealsToString(dbAccess.getPersonMeals(Person.WILL)));
		System.out.println("===========================================");
	}

	private void printAllMeals() {
		System.out.println("All meals");
		System.out.println("===========================================");
		System.out.println(mealsToString(dbAccess.getAllMeals()));
		System.out.println("===========================================");
		
	}
	
	private String mealsToString(ArrayList<Meal> meals) {
		
		if(meals.size() == 0){
			return "NO MEALS TO DISPLAY";
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(Meal m : meals){
			sb.append(m + "\n");
		}
		sb.deleteCharAt(sb.length()-1);
		
		return sb.toString();
	}

}
