package framework;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

import util.Util;
import message.MessageType;
import message.MethodCall;
import message.RMIMessage;
import message.response.MethodReturn;

public class RemoteInvocationHandler implements InvocationHandler {

	private String host;
	private int port;
	private Socket socket = null;
	private RemoteObjectRef ror = null;

	public RemoteInvocationHandler(RemoteObjectRef ror) {
		this.host = ror.getHost();
		this.port = ror.getPort();
		this.ror = ror;
		try {
			this.setSocket(new Socket(this.host, this.port));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		MethodCall methodCall = new MethodCall();
		methodCall.setArgs(args);
		methodCall.setMethod(method.getName());
		methodCall.setRor(ror);
		Util.writeMessage(socket, methodCall);
		RMIMessage message = Util.readMessage(socket);
		if (message.getType() != MessageType.METHOD_RETURN) {
			return null;
		} else {
			return (MethodReturn) message;
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
