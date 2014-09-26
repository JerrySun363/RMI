package framework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import message.RMIMessage;

/**
 * This class entends Thread to implement multi-thread dealing with RMI Look up.
 * 
 * Since it is in multi threads mode now, we should be careful about the
 * concurrency issues.
 * 
 * @author Jerry
 * 
 */
public class ListenerForRMI extends Thread {

	private Socket socket;
	private RORTable table;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public ListenerForRMI(RORTable table, Socket socket) throws IOException {
		this.socket = socket;
		this.table = table;
		// (2) creates a socket and input/output streams.
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}

	@Override
	public void run(){

		// (3) gets the invocation, in martiallled form.
		try {
			RMIMessage message = (RMIMessage) in.readObject();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public void processMessage(RMIMessage m){
		switch (m.getType()){
			
		}
	}
	
	
}
