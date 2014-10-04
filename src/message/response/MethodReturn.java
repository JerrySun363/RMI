package message.response;

import message.MessageType;
import framework.RemoteObjectRef;

public class MethodReturn extends AbstractResponse {

	private static final long serialVersionUID = 2176958159170938794L;
	private Object object;
	private RemoteObjectRef ror;
	private boolean isROR;

	public MethodReturn(Object returnObject) {
		super();
		super.setType(MessageType.METHOD_RETURN);
		this.setObject(returnObject);
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public RemoteObjectRef getRor() {
		return ror;
	}

	public void setRor(RemoteObjectRef ror) {
		this.ror = ror;
	}

	public boolean isROR() {
		return isROR;
	}

	public void setROR(boolean isROR) {
		this.isROR = isROR;
	}
	
}
