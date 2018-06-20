package app;

public class Timer implements Runnable{

	private volatile boolean keepingTime;
	private int secCounter;
	
	public Timer(){
		this.keepingTime = true;
		this.secCounter = -1;
	}
	
	public void toggleTimer(){
		this.keepingTime = !this.keepingTime;
		if(keepingTime){
			System.out.println("The timer is now on.");
		}
		else{
			System.out.println("The timer is now off.");
			secCounter = 0;
		}
	}
	
	public void viewRunTime(){
		if(!keepingTime){
			System.out.println("The time has been switched off :(");
		}
		else{
			System.out.println("Number of seconds timer has been running: " + secCounter);
		}
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
				if(keepingTime){
					secCounter++;
				}
				
			} catch (InterruptedException e) {
				//wont happen
			}
			
		}
	}
		
	

}
