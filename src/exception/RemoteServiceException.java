package exception;

import java.io.Serializable;

/**
 * Comment on the exception
 * 
 * @author Jerry
 * 
 */
public class RemoteServiceException extends Exception implements Serializable {

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
