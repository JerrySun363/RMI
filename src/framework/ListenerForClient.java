package framework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

import framework.registry.RMI;
import message.MethodCall;
import message.RMIMessage;
import message.response.MethodReturn;

/**
 * This class entends Thread to implement multi-thread dealing with RMI Look up.
 * 
 * Since it is in multi threads mode now, we should be careful about the
 * concurrency issues.
 * 
 * @author Jerry
 * 
 */
public class ListenerForClient extends Thread {

	private Socket socket;
	private RORTable table;

	public ListenerForClient(RORTable table, Socket socket) throws IOException {
		this.table = table;
		this.socket = socket;
		// (2) creates a socket and input/output streams.
	}

	@Override
	public void run() {

		// (3) gets the invocation, in martiallled form.
		try {
			ObjectInputStream in = new ObjectInputStream(
					socket.getInputStream());
			RMIMessage message = (RMIMessage) in.readObject();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception e2) {
				log("Couldn't close a socket, what's going on?");
			}
			log("Connection with client# " + " " + " closed");
		}

		// (4) gets the real object reference from tbl.

		// (5) Either:
		// -- using the interface name, asks the skeleton,
		// together with the object reference, to unmartial
		// and invoke the real object.
		// -- or do unmarshalling directly and involkes that
		// object directly.
		// (6) receives the return value, which (if not marshalled
		// you should marshal it here) and send it out to the
		// the source of the invoker.
		// (7) closes the socket.

	}

	private void log(String info) {
		System.out.println(info);
	}

	/**
	 * TODO: Add return support.
	 * @param m
	 * @return
	 */
	public MethodReturn processMessage(MethodCall m){
		// MethodCall
		if(m.getRor()==null){
			return null;
		}
		
		Object object = this.table.findObj(m.getRor());
		if(object == null){
			return null;
		}
		try {
			Object[] args = m.getArgs();
			Class<?>[] type = new Class[args.length];
			int len = args.length;
			for (int i = 0; i < len; i++) {
				type[i] = args[i].getClass();
			}
			Method method = object.getClass().getMethod(m.getMethod(), type);
			Object returnObject = method.invoke(object, m.getArgs());
			MethodReturn mr = new MethodReturn(returnObject);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}