package framework;

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

public class RMI {
	private static String host;
	private static int port;
	private static int DEFAULT_PORT = 15640;

	/**
	 * It will use a hash table, which contains ROR together with reference to
	 * the remote object. 
	 * As you can see, the exception handling is not done at all.
	 * 
	 * @param args
	 * @throws Exception
	 * @author Jerry Sun
	 */
	public static void main(String args[]) throws Exception {
		String InitialClassName = args[0];
		String registryHost = args[1];
		int registryPort = Integer.parseInt(args[2]);
		String serviceName = args[3];

		host = (InetAddress.getLocalHost()).getHostName();
		port = RMI.DEFAULT_PORT;

		// it now have two classes from MainClassName:
		// (1) the class itself (say ZipCpdeServerImpl) and
		// (2) its skeleton.
		Class initialclass = Class.forName(InitialClassName);
		Class initialskeleton = Class.forName(InitialClassName + "_skel");

		// you should also create a remote object table here.
		// it is a table of a ROR and a skeleton.
		// as a hint, I give such a table's interface as RORtbl.java.
		RORTable table = new RORTable();

		// after that, you create one remote object of initialclass.
		Object o = initialclass.newInstance();

		// then register it into the table.
		table.addObj(host, port, o);
		
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
			
		}
	}
}
