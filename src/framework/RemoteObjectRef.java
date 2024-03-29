package framework;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * We rewrite the ROR's equal method in this class, so that we can omit the
 * objkey field.
 * 
 * @author Nicolas Yu
 * 
 */
public class RemoteObjectRef implements Serializable {

	private static final long serialVersionUID = 8035678189759683561L;
	private String host;
	private int port;
	private String remoteInterfaceName;

	public int getPort() {
		return port;
	}

	public String getRemoteInterfaceName() {
		return remoteInterfaceName;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setRemoteInterfaceName(String remoteInterfaceName) {
		this.remoteInterfaceName = remoteInterfaceName;
	}

	public RemoteObjectRef(String host, int port, String riname) {
		this.host = host;
		this.port = port;
		// this.objKey = objKey;
		this.remoteInterfaceName = riname;
	}

	/**
	 * 
	 * @return localized object
	 */
	public Object localize() {
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
		info += "Interface Name : " + this.remoteInterfaceName + "\n";
		return info;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + port;
		result = prime
				* result
				+ ((remoteInterfaceName == null) ? 0 : remoteInterfaceName
						.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RemoteObjectRef other = (RemoteObjectRef) obj;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (port != other.port)
			return false;
		if (remoteInterfaceName == null) {
			if (other.remoteInterfaceName != null)
				return false;
		} else if (!remoteInterfaceName.equals(other.remoteInterfaceName))
			return false;
		return true;
	}
}
