package framework.registry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;

import message.BindMessage;
import message.LocateMessage;
import message.LookupMessage;
import message.RMIMessage;
import message.RebindMessage;
import message.UnbindMessage;
import message.response.BindResponse;
import message.response.LocateResponse;
import message.response.LookupResponse;
import message.response.RebindResponse;
import message.response.UnbindResponse;
import exception.RemoteServiceException;
import framework.RemoteObjectRef;

/**
 * Registry Server side single thread handler for simple operation.
 * 
 * @author Jerry Sun
 * 
 */
public class RegistryHandler implements Runnable {

	private Socket socket;
	private Hashtable<String, RemoteObjectRef> table;
	private RMIMessage message;

	public RegistryHandler(Socket socket,
			Hashtable<String, RemoteObjectRef> table) {
		this.socket = socket;
		this.table = table;
	}

	@Override
	public void run() {
		ObjectInputStream in;

		try {
			in = new ObjectInputStream(socket.getInputStream());

			log("Accept socket from one client");
			// (3) gets the invocation, in martiallled form.
			message = (RMIMessage) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RMIMessage msg = null;
		switch (this.message.getType()) {
		case BIND:
			System.out.println("Receive Bind Message!");
			msg = this.bind((BindMessage) message);
			break;
		case LOCATE:
			System.out.println("Receive Locate Message!");
			msg = this.locate((LocateMessage) message);
			break;
		case LOOKUP:
			System.out.println("Receive Lookup Message!");
			msg = this.lookup((LookupMessage) message);
			break;
		case REBIND:
			msg = this.rebind((RebindMessage) message);
			System.out.println("Receive Rebind Message!");
			break;
		case UNBIND:
			msg = this.unbind((UnbindMessage) message);
			System.out.println("Receive Unbind Message!");
			break;
		default:
			System.out.println("Unknown Message Type! It will be ignored!");
			break;
		}
		if (msg != null) {
			try {
				ObjectOutputStream out = new ObjectOutputStream(
						this.socket.getOutputStream());
				out.writeObject(msg);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private RMIMessage lookup(LookupMessage message) {
		LookupResponse lp = new LookupResponse();
		try {
			String serviceName = message.getServiceName();
			synchronized (this.table) {
				if (this.table.containsKey(serviceName)) {
					lp.setROR(this.table.get(serviceName));
				}
			}
		} catch (Exception e) {
			lp.setSuccess(false);
			lp.setException(new RemoteServiceException(e.getMessage()));
		}
		return lp;
	}

	private RMIMessage bind(BindMessage message) {
		BindResponse br = new BindResponse();
		try {
			String serviceName = message.getName();
			RemoteObjectRef ror = message.getRor();
			this.table.put(serviceName, ror);
		} catch (Exception e) {
			br.setSuccess(false);
			br.setException(new RemoteServiceException(e.getMessage()));
		}
		return br;
	}

	private RMIMessage rebind(RebindMessage rebindMessage) {
		RebindResponse rr = new RebindResponse();
		try {
			String serviceName = rebindMessage.getServiceName();
			RemoteObjectRef ror = rebindMessage.getROR();
			this.table.put(serviceName, ror);
		} catch (Exception e) {
			rr.setSuccess(false);
			rr.setException(new RemoteServiceException(e.getMessage()));
		}
		return rr;
	}

	private RMIMessage unbind(UnbindMessage unbindMessage) {
		UnbindResponse unbindResponse = new UnbindResponse();
		try {
			String name = unbindMessage.getServiceName();
			synchronized (this.table) {
				if (this.table.containsKey(name)) {
					this.table.remove(name);
				}
			}
			return unbindResponse;
		} catch (Exception e) {
			unbindResponse.setSuccess(false);
			unbindResponse.setException(new RemoteServiceException(e
					.getMessage()));
		}
		return unbindResponse;
	}

	private RMIMessage locate(LocateMessage locateMessage) {
		LocateResponse lr = new LocateResponse();
		lr.setResponse(locateMessage.getChallenge()
				* locateMessage.getChallenge());
		return lr;
	}

	private void log(String info) {
		System.out.println(info);
	}

}
