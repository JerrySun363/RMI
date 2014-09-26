package message;

import java.lang.reflect.Method;

/**
 * 
 * @author Jerry
 * 
 *         TODO: Add implementation
 * 
 */
public class MethodCall extends RMIMessage {

	private static final long serialVersionUID = 3023192217054714936L;
	private Method method;
	private Object[] args;

	public MethodCall() {
		super();
		super.setType(MessageType.METHOD);
	}

	public MethodCall(Method m, Object[] args) {
		this();
		this.setArgs(args);
		this.setMethod(m);

	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
}
