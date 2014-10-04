package framework;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import util.Remote;

public class RemoteObjectRef implements Remote, Serializable {
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

	public RemoteObjectRef(String host, int port, String riname) {
		this.host = host;
		this.port = port;
		//this.objKey = objKey;
		this.remoteInterfaceName = riname;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
	        return false;
	    }
	    RemoteObjectRef newObj = (RemoteObjectRef) obj;
		
	    if (!this.host.equals(newObj.getHost()) || this.port != newObj.getPort() 
	    			|| !this.remoteInterfaceName.equals(newObj.getRemoteInterfaceName())) {
	        return false;
	    }
	    return true;
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
