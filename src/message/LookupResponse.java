package message;

import framework.RemoteObjectRef;

public class LookupResponse extends RMIMessage {

	private static final long serialVersionUID = 2197270421502550217L;
	private RemoteObjectRef ror = null;

	public LookupResponse() {
		super();
		super.setType(MessageType.LOOKUP_RESPONSE);
	}

	public LookupResponse(RemoteObjectRef ror) {
		this();
		this.setROR(ror);
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
