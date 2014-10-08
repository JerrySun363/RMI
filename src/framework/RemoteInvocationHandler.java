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
		Socket socket = new Socket(this.host, this.port);
		MethodCall methodCall = new MethodCall();
		methodCall.setArgs(args);
		methodCall.setMethod(method.getName());
		methodCall.setRor(ror);
		ObjectOutputStream out = new ObjectOutputStream(
				socket.getOutputStream());
		out.writeObject(methodCall);
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		RMIMessage message = (RMIMessage) in.readObject();
		socket.close();
		if (message.getType() != MessageType.METHOD_RETURN) {
			return null;
		} else {
			Object obj = ((MethodReturn) message).getObject();
			if (obj instanceof RemoteObjectRef) {
				return ((RemoteObjectRef) obj).localise();
			} else {
				return obj;
			}

		}

	}
}
