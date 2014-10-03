package framework.registry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sun.util.logging.resources.logging;
import framework.RemoteObjectRef;

/**
 * This class serves as the server side of the RMIRegistry. Should be called
 * through RMI main framework or progress.
 * 
 * @author Jerry Sun
 * 
 */
public class RMIRegistry implements Runnable {
	private static final int DEFAULT_PORT = 1099;
	private ServerSocket serverSocket = null;
	private Hashtable<String, RemoteObjectRef> binding;

	public RMIRegistry() {
		this(DEFAULT_PORT);
	}

	public RMIRegistry(int port) {
		try {
			this.serverSocket = new ServerSocket(port);
			this.binding = new Hashtable<>();
		} catch (IOException e) {
			System.out.println("Error initializing new server socket!");
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = this.serverSocket.accept();
				System.out.println(socket);
				new RegistryHandler(socket, binding).run();
				
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
