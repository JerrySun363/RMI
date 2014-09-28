package message.response;

import message.MessageType;

/**
 * 
 * @author Jerry
 * 
 */
public class RebindResponse extends AbstractResponse {

	private static final long serialVersionUID = -1562062519610354705L;
	private boolean success = true;

	public RebindResponse() {
		super();
		super.setType(MessageType.REBIND_RESPONSE);
	}

	public RebindResponse(boolean success) {
		this();
		this.setSuccess(success);
	}

}
