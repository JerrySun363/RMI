package tst.ZipCode;

// in implementation, you do not have to extend this as in Java RMI. 
// in your design, however, you can do so.
// it is assumed that this is not directly called but as in:
//
//   java yourRMI ZipCodeServerImpl registryhost resigstryport servicename
//
// therefore it does not contain main: new object creation, binding etc. is 
// done via your RMI.

public class ZipCodeServerImpl implements ZipCodeServer {
	ZipCodeList l;

	// this is a constructor.
	public ZipCodeServerImpl() {
		l = null;
	}

	// when this is called, marshalled data
	// should be sent to this remote object,
	// and reconstructed.
	public void initialise(ZipCodeList newlist) {
		l = newlist;
	}

	// basic function: gets a city name, returns the zip code.
	public String find(String request) {
		// search the list.
		ZipCodeList temp = l;
		while (temp != null && !temp.getCity().equals(request))
			temp = temp.getNext();

		// the result is either null or we found the match.
		if (temp == null)
			return null;
		else
			return temp.getZipCode();
	}

	// this very short method should send the marshalled
	// whole list to the local site.
	public ZipCodeList findAll() {
		return l;
	}

	// this method does printing in the remote site, not locally.
	public void printAll() {
		ZipCodeList temp = l;
		while (temp != null) {
			System.out.println("city: " + temp.getCity() + ", " + "code: "
					+ temp.getZipCode() + "\n");
			temp = temp.getNext();
		}
	}

	public Integer sum(Integer int1, Integer int2) {
		return int1 + int2;
	}
}
