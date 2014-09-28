package framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import util.Remote;

public class RemoteObjectRef implements Remote {
	private String host;
	private int port;
	private int objKey;
	private String remoteInterfaceName;

	public int getPort() {
		return port;
	}

	public int getObjKey() {
		return objKey;
	}

	public String getRemoteInterfaceName() {
		return remoteInterfaceName;
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

	public RemoteObjectRef(String host, int port, int objKey, String riname) {
		this.host = host;
		this.port = port;
		this.objKey = objKey;
		this.remoteInterfaceName = riname;
	}

	/**
	 * 
	 * @return localised object
	 */
	public Object localise() {
		Object object = null;

		try {
			InvocationHandler handler = new RemoteInvocationHandler(this);

			Class<?> proxyClass = Class.forName(this.getRemoteInterfaceName());

			object = Proxy.newProxyInstance(proxyClass.getClassLoader(),
					new Class[] { proxyClass }, handler);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return object;
	}

	public String toString() {
		String info = "";
		info += "IP Address : " + this.host + "\n";
		info += "Port Number : " + this.port + "\n";
		info += "Object Key : " + this.objKey + "\n";
		info += "Interface Name : " + this.remoteInterfaceName + "\n";
		return info;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
