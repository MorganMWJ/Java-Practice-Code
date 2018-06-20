package cs21120.depq.run;

import java.util.Scanner;

import cs21120.depq.Mwj7DEPQ;


public class Application {

	private Scanner scan;
	private Mwj7DEPQ queue;
	
	public Application(){
		this.queue = new Mwj7DEPQ(); 
	}
	
	public static void main(String[] args) {
		Application program = new Application();
		System.out.println("Binary Search Tree - Double Ended Prioity Queue Created.\n");
		program.chooseOperations();
		program.finalOutput();
		System.out.println("\nProgram Over.");
	}
	
	private void chooseOperations(){
		this.scan = new Scanner(System.in);
		while(true){
			switch (getUserInput()){
				case '1': //add to DEPQ
					System.out.println("Add an element: ");
					int element = Integer.parseInt(scan.nextLine());
					queue.add(element);
					break;
				case '2':	
					System.out.println("Least: " + queue.inspectLeast());
					break;
				case '3':
					System.out.println("Most: " + queue.inspectMost());
					break;
				case '4':
					System.out.println(queue.getLeast() + " REMOVED!");
					break;
				case '5':
					System.out.println(queue.getMost() + " REMOVED!");
					break;
				case '6':
					queue.clear();
					System.out.println("Queue Cleared!");
					break;					
				case 'q': 	
					this.scan.close();
					return;				
			}
			//queue.debugInstance();
		}
	}

	private char getUserInput() {
		//////////////////////////
		System.out.println("1. Add element");
		System.out.println("2. Inspect Least");
		System.out.println("3. Inspect Most");
		System.out.println("4. Get Least");
		System.out.println("5. Get Most");
		System.out.println("6. Clear");
		System.out.println("q. Quit");
		////////////////////////////
		char input;
		boolean badInput=true;
		do{
			System.out.println("Select an operation to perform:\n");
			input = scan.nextLine().charAt(0);
			if(input == '1' || input == '2' || input == '3' || input == '4' || input == '5' || input == '6'|| input == 'q'){
				badInput=false;
			}
		}while(badInput);
		return input;
	}

	private void finalOutput() {
		System.out.println("size: " + queue.size());
		System.out.println("inspect least: " +  queue.inspectLeast());
		System.out.println("inspect most: " +  queue.inspectMost());
		System.out.println("Is Emtpy: " +  queue.isEmpty());
	}

}
