package exception;

/**
 * TODO: Comment on the exception
 * 
 * @author Jerry
 * 
 */
public class RemoteServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3727096976172335200L;
	String cause = null;

	public RemoteServiceException(String cause) {
		this.cause = cause;
	}

	public String toString() {
		return this.cause;
	}
}
