package message.response;

import message.MessageType;

public class UnbindResponse extends AbstractResponse {

	private static final long serialVersionUID = -6727115894978505081L;

	public UnbindResponse() {
		super();
		super.setType(MessageType.REBIND_RESPONSE);
	}

	public UnbindResponse(boolean success) {
		this();
		this.setSuccess(success);
	}

}
