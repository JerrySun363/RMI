package message;

public class UnbindResponse extends AbstractResponse {

	private static final long serialVersionUID = -6727115894978505081L;

	private UnbindResponse() {
		super();
		super.setType(MessageType.REBIND_RESPONSE);
	}

	private UnbindResponse(boolean success) {
		this();
		this.setSuccess(success);
	}

}
