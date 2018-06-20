package app;

import java.util.Scanner;

public class Application {

	private Timer timer;
	private int value;
	
	public Application(){
		this.value = 0;
		this.timer = new Timer();
	}
	
	public Timer getTimer(){
		return timer;
	}
	
	public void addToValue(int amount){
		value+=amount;
		System.out.printf("%d has been added, the value is now: %d\n", amount, value);
	}
	
	public void printMenu(){
		System.out.println("Please choose from the menu:");
		System.out.printf("1. %s\n2. %s\n3. %s\n4. %s\n", "Add Amount", "Toggle Timer", "View running time", "Quit");
	}
	
	public static void main(String[] args) {
		Application app = new Application();
		Thread timerThread = new Thread(app.getTimer());
		timerThread.start();
		
		Scanner stdin = new Scanner(System.in);
		boolean quit = false;
		
		while(!quit){
			app.printMenu();
			char choice = stdin.next().charAt(0);
			switch(choice){
			case '1':
				System.out.println("Please enter the amount: ");
				int input = stdin.nextInt();
				app.addToValue(input);
				break;
			case '2':
				app.getTimer().toggleTimer();
				break;
			case '3':
				app.getTimer().viewRunTime();
				break;
			case '4':
				quit = true;
				break;
			default:
				System.out.println("Not valid choice.");
			}
		}
		stdin.close();
	}

}
