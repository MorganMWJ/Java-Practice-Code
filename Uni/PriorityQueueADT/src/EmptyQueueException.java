
public class EmptyQueueException extends RuntimeException{
	//extending RuntimeException instead of Exception means
	//that the exception doesn't need to be handled
	
	public EmptyQueueException(String msg){
		super(msg);
	}
	
	public EmptyQueueException(){
		super();
	}

}
