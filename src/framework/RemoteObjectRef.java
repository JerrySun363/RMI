package framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import util.Remote;

public class RemoteObjectRef implements Remote {
	private String host;
	private int port;
	// private int objKey;
	// private String RemoteInterfaceName;
	private String ipAddr;

	public String getIpAddr() {
		return ipAddr;
	}

	public int getPort() {
		return port;
	}

	public int getObjKey() {
		return objKey;
	}

	public String getRemoteInterfaceName() {
		return remoteInterfaceName;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setObjKey(int objKey) {
		this.objKey = objKey;
	}

	public void setRemoteInterfaceName(String remoteInterfaceName) {
		this.remoteInterfaceName = remoteInterfaceName;
	}

	private int objKey;
	private String remoteInterfaceName;

	public RemoteObjectRef(String host, int port, int objKey, String riname) {
		this.host = host;
		this.port = port;
		// this.objKey = objKey;
		this.remoteInterfaceName = riname;
	}

	
	/**
	 * 
	 * @return localised object
	 */
	public Object localise() {
		Object object = null;
		try {
			Class<?> c = Class.forName(remoteInterfaceName + "Stub");
			Constructor<?> cons = c.getConstructor(String.class, Integer.class);
			object = cons.newInstance(this.host, this.port);

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return object;

	}

	public String toString() {
		String info = "";
		info += "IP Address : " + this.host + "\n";
		info += "Port Number : " + this.port + "\n";
		// info += "Object Key : " + this.objKey + "\n";
		// info += "Interface Name : " + this.RemoteInterfaceName + "\n";
		return info;
	}
}
