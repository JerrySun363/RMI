package message.response;

import exception.RemoteServiceException;
import message.RMIMessage;

public abstract class AbstractResponse extends RMIMessage {

	private static final long serialVersionUID = -8814189361216742729L;
	private boolean success = true;
	private RemoteServiceException exception = null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public RemoteServiceException getException() {
		return exception;
	}

	public void setException(RemoteServiceException exception) {
		this.exception = exception;
	}
}
