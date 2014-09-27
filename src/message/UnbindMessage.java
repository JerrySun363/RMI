package message;

import framework.RemoteObjectRef;

public class UnbindMessage extends RMIMessage {
	private String serviceName = null;
	private static final long serialVersionUID = 6160878511891744943L;

	public UnbindMessage() {
		super();
		super.setType(MessageType.UNBIND);
	}

	public UnbindMessage(String serviceName) {
		this();
		this.setServiceName(serviceName);

	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
