package message;

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

	public MethodCall() {
		super();
		super.setType(MessageType.METHOD);
	}

	public MethodCall(String m, Object[] args) {
		this();
		this.setArgs(args);
		this.setMethod(m);

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
}
