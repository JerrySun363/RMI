package message;

import framework.RemoteObjectRef;

/**
 * Rebind Message to tell which service and service to rebinds
 * 
 * @author Jerry
 * 
 */
public class RebindMessage extends RMIMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5872934610546443553L;
	private String serviceName = null;
	private RemoteObjectRef ror = null;

	public RebindMessage() {
		super();
		super.setType(MessageType.REBIND);
	}

	public RebindMessage(String serviceName, RemoteObjectRef ror) {
		this();
		this.setServiceName(serviceName);
		this.setROR(ror);
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public RemoteObjectRef getROR() {
		return ror;
	}

	public void setROR(RemoteObjectRef ror) {
		this.ror = ror;
	}

}
