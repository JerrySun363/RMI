package framework;

import java.net.*;
import java.io.*;

public class LocateSimpleRegistry {
	/**
	 * This is the SOLE static method. you use it as:
	 * LocateSimpleRegistry.getRegistry(123.123.123.123, 2048) and it returns
	 * null if there is none, else it returns the registry.
	 * 
	 * @param host
	 * @param port
	 * @return SimpleRegistry that is resisted with this host and port
	 */
	public static SimpleRegistry getRegistry(String host, int port) {
		// open socket.
		try {
			Socket socket = new Socket(host, port);
			ObjectOutputStream output = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(
					socket.getInputStream());
			
			
			// Get TCP streams and wrap them.
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			// Ask.
			out.println("Who are you?");

			// Gets answer.
			if ((in.readLine()).equals("I am a simple registry.")) {
				return new SimpleRegistry(host, port);
			} else {
				System.out.println("somebody is there but not a registry!");
				return null;
			}
		} catch (Exception e) {
			System.out.println("nobody is there!" + e);
			return null;
		}
	}
}
