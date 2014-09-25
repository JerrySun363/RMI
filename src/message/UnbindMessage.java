package message;

import framework.RemoteObjectRef;

public class UnbindMessage extends RMIMessage {
	private String serviceName = null;
	private RemoteObjectRef ror = null;
	private static final long serialVersionUID = 6160878511891744943L;

	public UnbindMessage() {
		super();
		super.setType(MessageType.UNBIND);
	}

	public UnbindMessage(String serviceName, RemoteObjectRef ror) {
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
