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
		
		int serviceNum = (args.length-2)/2;
		
		HashMap<String, String> serviceClassMap = new HashMap<String, String>();
		for (int i = 0; i < serviceNum; i++) {
			serviceClassMap.put(args[2+i*2], args[3+i*2]);
		}

		host = registryHost;
		port = registryPort;

		(new RMIRegistry()).start();
		
		SimpleRegistry simpleRegistry = new SimpleRegistry("localhost", 1099);
		
		RORTable table = new RORTable();
		
		for (String serviceName : serviceClassMap.keySet()) {
			Object o = Class.forName(serviceClassMap.get(serviceName)).newInstance();
			RemoteObjectRef ror = new RemoteObjectRef(host, port, serviceName);
			table.addObj(ror, o);
			simpleRegistry.bind(serviceName, ror);
		}

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
