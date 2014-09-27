package message.response;

import message.MessageType;

public class BindResponse extends AbstractResponse {
	public BindResponse() {
		super();
		super.setType(MessageType.BIND_RESPONSE);
	}

	private static final long serialVersionUID = 1L;

}
