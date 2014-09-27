package message;

import framework.RemoteObjectRef;

public class BindMessage extends RMIMessage {
	private static final long serialVersionUID = 7893992387246114677L;
	private String name;
	private RemoteObjectRef ror;

	public BindMessage(String name, RemoteObjectRef ror) {
		super();
		super.setType(MessageType.BIND);
		this.name = name;
		this.ror = ror;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RemoteObjectRef getRor() {
		return ror;
	}

	public void setRor(RemoteObjectRef ror) {
		this.ror = ror;
	}

}
