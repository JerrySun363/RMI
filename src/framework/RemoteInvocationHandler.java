package framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

import message.MessageType;
import message.MethodCall;
import message.RMIMessage;
import message.response.MethodReturn;

/**
 * The invocation handler is called when we need to associate a proxy instance
 * with a new interface.
 * 
 * @author Jerry Sun
 * 
 */
public class RemoteInvocationHandler implements InvocationHandler {

	private String host;
	private int port;
	private RemoteObjectRef ror = null;

	public RemoteInvocationHandler(RemoteObjectRef ror) {
		this.host = ror.getHost();
		this.port = ror.getPort();
		this.ror = ror;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// open the socket.
		Socket socket = new Socket(this.host, this.port);
		// form the message
		MethodCall methodCall = new MethodCall();
		methodCall.setArgs(args);
		methodCall.setMethod(method.getName());
		methodCall.setRor(ror);
		// write the message
		ObjectOutputStream out = new ObjectOutputStream(
				socket.getOutputStream());
		out.writeObject(methodCall);
		// block and read message
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		RMIMessage message = (RMIMessage) in.readObject();
		socket.close();
		// judge the message type.
		if (message.getType() != MessageType.METHOD_RETURN) {
			return null;
		} else if (!((MethodReturn) message).isSuccess()) {
			// judge whether it is successful
			// if not successful, throw new exception.
			throw ((MethodReturn) message).getException();
		} else {
			// get the object
			Object obj = ((MethodReturn) message).getObject();
			if (obj instanceof RemoteObjectRef) {
				// if it is RemoteObjectRef, return its localized version.
				return ((RemoteObjectRef) obj).localize();
			} else {
				// or just return the object itself
				return obj;
			}

		}

	}
}
