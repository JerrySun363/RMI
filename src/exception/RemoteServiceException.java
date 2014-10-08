package exception;

import java.io.Serializable;

/**
 * This exception class will wrap the any exception message generated at the
 * server side and return as a unified exception class.
 * 
 * 
 * 
 * @author Jerry Sun
 * 
 */
public class RemoteServiceException extends Exception implements Serializable {

	private static final long serialVersionUID = -3727096976172335200L;
	String cause = null;

	/**
	 * 
	 * @param cause
	 *            - the reason of the exception. Customered by specific
	 *            exception.
	 */
	public RemoteServiceException(String cause) {
		this.cause = cause;
	}

	public String toString() {
		return this.cause;
	}
}
