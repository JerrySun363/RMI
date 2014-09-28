package framework;

import util.Remote;

public class RemoteObjectRef implements Remote {
	private String host;
	private int port;
	//private int objKey;
	//private String RemoteInterfaceName;
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

	private int port;
	private int objKey;
	private String remoteInterfaceName;

	public RemoteObjectRef(String host, int port, int objKey, String riname) {
		this.host = host;
		this.port = port;
		//this.objKey = objKey;
		//this.RemoteInterfaceName = riname;
	}

	// this method is important, since it is a stub creator.
	//
	public Object localise() {
		// Implement this as you like: essentially you should
		// create a new stub object and returns it.
		// Assume the stub class has the name e.g.
		//
		// Remote_Interface_Name + "_stub".
		//
		// Then you can create a new stub as follows:
		//
		// Class c = Class.forName(Remote_Interface_Name + "_stub");
		// Object o = c.newinstance()
		//
		// For this to work, your stub should have a constructor without
		// arguments.
		// You know what it does when it is called: it gives communication
		// module
		// all what it got (use CM's static methods), including its method name,
		// arguments etc., in a marshalled form, and CM (yourRMI) sends it out
		// to
		// another place.
		// Here let it return null.
		return null;
	}

	public String toString() {
		String info = "";
		info += "IP Address : " + this.host + "\n";
		info += "Port Number : " + this.port + "\n";
		//info += "Object Key : " + this.objKey + "\n";
		//info += "Interface Name : " + this.RemoteInterfaceName + "\n";
		return info;
	}
}
