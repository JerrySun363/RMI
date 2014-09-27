package message.response;

import message.MessageType;

public class LocateResponse extends AbstractResponse {
	private static final long serialVersionUID = 8629804322597669115L;
	private int response = 0;

	public LocateResponse() {
		super();
		super.setType(MessageType.LOCATE_RESPONSE);
	}

	public int getResponse() {
		return response;
	}

	public void setResponse(int response) {
		this.response = response;
	}

	public LocateResponse(int response) {
		this();
		this.setResponse(response);
	}

}
