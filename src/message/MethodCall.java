package message;

import framework.RemoteObjectRef;

/**
 * 
 * @author Jerry
 * 
 *         TODO: Add implementation
 * 
 */
public class MethodCall extends RMIMessage {

	private static final long serialVersionUID = 3023192217054714936L;
	private String methodName;
	private Object[] args;
	private RemoteObjectRef ror;

	public MethodCall() {
		super();
		super.setType(MessageType.METHOD);
	}

	public MethodCall(String m, Object[] args, RemoteObjectRef ror) {
		this();
		this.args = args;
		this.methodName = m;
		this.ror = ror;

	}

	public String getMethod() {
		return this.methodName;
	}

	public void setMethod(String method) {
		this.methodName = method;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getMethodName() {
		return methodName;
	}

	public RemoteObjectRef getRor() {
		return ror;
	}

	public void setRor(RemoteObjectRef ror) {
		this.ror = ror;
	}
}
