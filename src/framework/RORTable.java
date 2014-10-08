package framework;

import java.util.*;

/**
 * As we already re-wrote the equals and hashcode method, we can directly use
 * ROR as the key here.
 * 
 * @author Nicolas Yu
 * 
 */
public class RORTable {
	Hashtable<RemoteObjectRef, Object> table;

	public RORTable() {
		this.table = new Hashtable<RemoteObjectRef, Object>();
	}

	public void addObj(RemoteObjectRef ror, Object o) {
		table.put(ror, o);
	}

	public Object findObj(RemoteObjectRef ror) {
		return table.get(ror);
	}
}
