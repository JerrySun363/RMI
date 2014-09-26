package message.response;

import message.MessageType;

public class LocateResponse extends AbstractResponse {
	public LocateResponse() {
		super();
		super.setType(MessageType.LOCATE_RESPONSE);
	}

	private static final long serialVersionUID = 8629804322597669115L;

}
