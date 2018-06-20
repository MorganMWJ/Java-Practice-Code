
public class EmptyStackException extends RuntimeException {
	
	//extending RuntimeException instead of Exception means
	//that the exception doesn't need to be handled
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyStackException(String msg){
		super(msg);
	}
}
