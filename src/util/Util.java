package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;

import message.RMIMessage;

/**
 * The Utility methods are used to operate and cache write/read operations.
 * 
 * We use map to cache the connection here. Thus it should be used to cache the
 * server side operation.
 * 
 * @author Jerry Sun
 * 
 */
public class Util {
	private static Hashtable<Socket, ObjectOutputStream> outputMap = new Hashtable<>();
	private static Hashtable<Socket, ObjectInputStream> inputMap = new Hashtable<>();

	public static void writeMessage(Socket socket, RMIMessage message) {
		ObjectOutputStream out = null;
		try {
			if (outputMap.containsKey(socket)) {
				out = outputMap.get(socket);
			} else {
				out = new ObjectOutputStream(socket.getOutputStream());
				outputMap.put(socket, out);
			}
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static RMIMessage readMessage(Socket socket) {
		ObjectInputStream in = null;
		RMIMessage message = null;
		try {
			if (inputMap.containsKey(socket)) {
				in = inputMap.get(socket);
			} else {
				in = new ObjectInputStream(socket.getInputStream());
			}
			message = (RMIMessage) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return message;
	}

	public static boolean closeSocket(Socket socket) {
		if (outputMap.containsKey(socket)) {
			outputMap.remove(socket);
		}
		if (inputMap.containsKey(socket)) {
			inputMap.remove(socket);
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
