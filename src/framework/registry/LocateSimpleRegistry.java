package framework.registry;

import java.net.*;
import java.io.*;

import message.LocateMessage;
import message.response.LocateResponse;

public class LocateSimpleRegistry {
	/**
	 * This is the SOLE static method. you use it as:
	 * LocateSimpleRegistry.getRegistry(123.123.123.123, 2048) and it returns
	 * null if there is none, else it returns the registry.
	 * 
	 * @param host
	 * @param port
	 * @return SimpleRegistry that is resisted with this host and port
	 * @throws IOException
	 */
	public static RegistryInterface getRegistry(String host, int port)
			throws IOException {
		// open socket.
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			ObjectOutputStream output = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(
					socket.getInputStream());

			int challenge = (int) Math.round(Math.random() * 30);
			LocateMessage locate = new LocateMessage(challenge);
			output.writeObject(locate);
			LocateResponse res = (LocateResponse) input.readObject();
			if (res.getResponse() == challenge * challenge) {
				return new SimpleRegistry(host, port);
			} else {
				System.out.println("Somebody is there, but not registry!");
				return null;
			}

		} catch (Exception e) {
			System.out.println("Nobody is there!" + e);
			return null;
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}
}
