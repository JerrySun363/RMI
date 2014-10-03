package framework.registry;

/** 
 * This does not offer the code of the whole communication module (CM) for RMI: 
 * but it gives some hints about how you can make it. I call it simply "yourRMI". 

 * For example, it shows how you can get the host name etc. 
 * or how you can make a class out of classname as a string.

 * This just shows one design option. Other options are possible.
 * We assume there is a unique skeleton for each remote object class (not object) which is called by CM
 *  by static methods for unmarshalling etc. 
 *  
 *  We can do without it, in which case CM does marshalling/unmarhshalling. 
 *  
 *  Which is simpler, I cannot say, since both have their own simpleness and complexity.
 */

import java.net.*;
import java.util.HashMap;

import framework.ListenerForClient;
import framework.RORTable;
import framework.RemoteObjectRef;

public class RMI {
	private static String host;
	private static int port;
	private static int DEFAULT_PORT = 15640;

	/**
	 * It will use a hash table, which contains ROR together with reference to
	 * the remote object. As you can see, the exception handling is not done at
	 * all.
	 * 
	 * @param args
	 * @throws Exception
	 * @author Jerry Sun
	 */
	public static void main(String args[]) throws Exception {

		String registryHost = args[0];
		int registryPort = Integer.parseInt(args[1]);

		int serviceNum = (args.length - 2) / 2;

//		HashMap<String, String> serviceName_class = new HashMap<String, String>();
//
//		for (int i = 0; i < serviceNum; i++) {
//			serviceName_class.put(args[2 + i], args[3 + i]);
//		}
		String serviceName = args[2];
		String InitialClassName = args[3];
		
		host = (InetAddress.getLocalHost()).getHostName();
		port = RMI.DEFAULT_PORT;

		
		RMIRegistry registry = new RMIRegistry();
		SimpleRegistry simpleRegistry = new SimpleRegistry("localhost", 1099);
		
		// it now have two classes from MainClassName:
		// (1) the class itself (say ZipCpdeServerImpl) and
		// (2) its skeleton.

		Class initialclass = null;
		//Class initialskeleton = null;

		//for (String className : serviceName_class.keySet()) {
		initialclass = Class.forName(InitialClassName);
			//initialskeleton = Class.forName(className + "_skel");
		//}

		// you should also create a remote object table here.
		// it is a table of a ROR and a skeleton.
		// as a hint, I give such a table's interface as RORtbl.java.
		RORTable table = new RORTable();

		// after that, you create one remote object of initialclass.
		Object o = initialclass.newInstance();

		// then register it into the table.
		RemoteObjectRef ror = new RemoteObjectRef(host, port, serviceName);
		table.addObj(ror, o);
		simpleRegistry.bind(serviceName, ror);

		// create a socket.
		ServerSocket serverSoc = new ServerSocket(port);

		// Now we go into a loop.
		// Look at rmiregistry.java for a simple server programming.
		//
		// The code is far from optimal but in any way you can get basics.
		// Actually you should use multiple threads, or this easily deadlocks.
		//
		// But for your implementation I do not ask it.
		// For design, consider well.
		while (true) {
			// (1) receives an invocation request.
			Socket socket = serverSoc.accept();
			new ListenerForClient(table, socket).start();
		}
	}
}
