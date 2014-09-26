package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;

import message.RMIMessage;

/**
 * The Utility methods are used to operate write/read operations
 * 
 * @author Jerry Sun
 * 
 */
public class Util {
	private static Hashtable<Socket, ObjectOutputStream> outputMap = new Hashtable<>();
	private static Hashtable<Socket, ObjectInputStream> inputMap = new Hashtable<>();

	public static void writeMessage(Socket socket, RMIMessage message) {
			
	}

	public static RMIMessage readMessage(Socket socket) {
		
		return null;
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
