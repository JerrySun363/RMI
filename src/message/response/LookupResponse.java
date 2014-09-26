package message.response;

import message.MessageType;
import framework.RemoteObjectRef;

public class LookupResponse extends AbstractResponse {

	private static final long serialVersionUID = 2197270421502550217L;
	private RemoteObjectRef ror = null;

	public LookupResponse() {
		super();
		super.setType(MessageType.LOOKUP_RESPONSE);
		super.setSuccess(false);
	}

	public LookupResponse(RemoteObjectRef ror) {
		this();
		this.setROR(ror);
		this.setSuccess(true);
	}

	public RemoteObjectRef getROR() {
		return ror;
	}

	/**
	 * Set RemoteObjectRef
	 * 
	 * @param ror
	 */
	public void setROR(RemoteObjectRef ror) {
		this.ror = ror;
	}

}
