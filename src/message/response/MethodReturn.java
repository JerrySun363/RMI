package message.response;

import framework.RemoteObjectRef;

public class MethodReturn extends AbstractResponse implements util.Remote{

	private static final long serialVersionUID = 2176958159170938794L;
	private Object object;
	private RemoteObjectRef ror;
	private boolean isROR;
	
	public MethodReturn (Object returnObject) {
		this.object = returnObject;
	}
}
