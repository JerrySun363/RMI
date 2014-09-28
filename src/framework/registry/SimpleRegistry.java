package framework.registry;

import java.net.*;
import java.io.*;

import exception.RemoteServiceException;
import framework.RemoteObjectRef;
import message.BindMessage;
import message.LookupMessage;
import message.MessageType;
import message.RMIMessage;
import message.RebindMessage;
import message.UnbindMessage;
import message.response.BindResponse;
import message.response.LookupResponse;
import message.response.RebindResponse;

/**
 * 
 * @author Jerry
 * @see {http://docs.oracle.com/javase/7/docs/api/java/rmi/Naming.html}
 */
public class SimpleRegistry implements RegistryInterface {
	// registry holds its port and host, and connects to it each time.
	private String host;
	private int port;
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;

	// Constructor
	public SimpleRegistry(String IPAdr, int portNum) {
		this.host = IPAdr;
		this.port = portNum;
	}

	public void initSocket() {
		try {
			socket = new Socket(this.host, this.port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			System.out.println("Unknown Host Exception when initSocket!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I/O Exception when initSocket!");
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see framework.registry.RegistryInterface#lookup(java.lang.String)
	 */
	@Override
	public RemoteObjectRef lookup(String serviceName)
			throws RemoteServiceException {
		if (serviceName == null || serviceName.length() == 0) {
			System.out.println("Please input valid service name!");
			return null;
		}

		System.out.println("Start look up service: " + serviceName);
		if (this.socket == null) {
			this.initSocket();
		}

		LookupMessage lookup = new LookupMessage(serviceName);
		this.writeMessage(lookup);
		System.out.println("Lookup Message Sent!");

		RMIMessage message = this.readMessage();
		if (message == null || message.getType() != MessageType.LOOKUP_RESPONSE) {
			System.out
					.println("Could Not find correct response!\nPlease try later");
			return null;
		}

		LookupResponse res = (LookupResponse) message;
		RemoteObjectRef ror = null;
		if (!res.isSuccess() || res.getException() != null) {
			throw res.getException();
		} else {
			ror = res.getROR();
		}
		return ror;
	}

	/* (non-Javadoc)
	 * @see framework.registry.RegistryInterface#bind(java.lang.String, framework.RemoteObjectRef)
	 */
	@Override
	public void bind(String serviceName, RemoteObjectRef ror)
			throws RemoteServiceException {
		// Check RoR.
		if (ror == null) {
			System.out.println("Please input valid Remote Object Reference!");
			return;
		}

		if (serviceName == null || serviceName.isEmpty()) {
			System.out.println("Please input valid service name!");
			return;
		}

		if (this.socket == null) {
			this.initSocket();
		}
		BindMessage message = new BindMessage(serviceName, ror);
		this.writeMessage(message);
		System.out.println("Bind Message sent!");

		/* Read response to determine the status of the operation! */
		RMIMessage response = (RMIMessage) this.readMessage();
		if (response == null || response.getType() != MessageType.BIND_RESPONSE) {
			System.out.println("Error in Reading Response!");
		}

		if (!((BindResponse) response).isSuccess()) {
			System.out.println("Bind Fails!");
			if (((BindResponse) response).getException() != null) {
				throw ((BindResponse) response).getException();
			}
		} else {
			System.out.println("Bind Success!");

		}

	}

	/* (non-Javadoc)
	 * @see framework.registry.RegistryInterface#rebind(java.lang.String, framework.RemoteObjectRef)
	 */
	@Override
	public void rebind(String serviceName, RemoteObjectRef ror)
			throws RemoteServiceException {
		// Check RoR.
		if (ror == null) {
			System.out.println("Please input valid Remote Object Reference!");
			return;
		}

		if (serviceName == null || serviceName.isEmpty()) {
			System.out.println("Please input valid service name!");
			return;
		}

		if (this.socket == null) {
			this.initSocket();
		}
		RebindMessage message = new RebindMessage(serviceName, ror);
		this.writeMessage(message);
		System.out.println("Rebind Message sent!");

		/* Read response to determine the status of the operation! */
		RMIMessage response = (RMIMessage) this.readMessage();
		if (response == null
				|| response.getType() != MessageType.REBIND_RESPONSE) {
			System.out.println("Error in Reading Response!");
		}

		if (((RebindResponse) response).isSuccess()) {
			System.out.println("Rebind Success!");
		} else {
			System.out.println("Rebind Fails!");
			if (((RebindResponse) response).getException() != null) {
				throw ((RebindResponse) response).getException();
			}
		}
	}

	/* (non-Javadoc)
	 * @see framework.registry.RegistryInterface#unbind(java.lang.String)
	 */
	@Override
	public void unbind(String serviceName) throws RemoteServiceException {
		if (serviceName == null || serviceName.isEmpty()) {
			System.out.println("Please input valid service name!");
			return;
		}

		if (this.socket == null) {
			this.initSocket();
		}
		UnbindMessage message = new UnbindMessage(serviceName);
		this.writeMessage(message);
		System.out.println("Unbind Message sent!");

		/* Read response to determine the status of the operation! */
		RMIMessage response = (RMIMessage) this.readMessage();
		if (response == null
				|| response.getType() != MessageType.UNBIND_RESPONSE) {
			System.out.println("Error in Reading Response!");
		}

		if (((BindResponse) response).isSuccess()) {
			System.out.println("Unbind Success!");
		} else {
			System.out.println("Unbind Fails!");
			if (((BindResponse) response).getException() != null) {
				throw ((BindResponse) response).getException();
			}
		}
	}

	/**
	 * Closes the socket. It will cause the
	 */
	public void destroy() {
		try {
			this.out = null;
			this.in = null;
			this.socket.close();
			this.socket = null;
		} catch (IOException e) {
			System.out.println("Error when destroying the socket!");
			e.printStackTrace();
		}
	}

	/**
	 * Utility method to read message
	 * 
	 * @param message
	 */
	private void writeMessage(RMIMessage message) {
		try {
			this.out.writeObject(message);
		} catch (IOException e) {
			System.out.println("Error when writing new Messages!");
			e.printStackTrace();
		}
	}

	/**
	 * Utility method to read message
	 * 
	 * @param message
	 */
	private RMIMessage readMessage() {
		RMIMessage message = null;
		try {
			message = (RMIMessage) (this.in.readObject());
		} catch (IOException e) {
			System.out.println("I/O Exception during read object!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Could not convert it to RMI Message!");
			e.printStackTrace();
		}
		return message;
	}

}
