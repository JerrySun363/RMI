package message;

import message.response.AbstractResponse;

public class ExceptionMessage extends AbstractResponse {
	private static final long serialVersionUID = -643245226312472170L;
	private String cause = "Unknown";

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public ExceptionMessage() {
		super();
		super.setType(MessageType.EXCEPTION);
	}

	public ExceptionMessage(String cause) {
		this();
		this.setCause(cause);
	}

}
