package framework;

import java.io.*;

import exception.RemoteServiceException;
import framework.registry.LocateSimpleRegistry;
import framework.registry.RegistryInterface;

// we test simple registry by looking up a service.

public class TestLookup {

	public static void main(String args[]) throws IOException, RemoteServiceException {
		// it takes three arguments.
		// these are it wishes to connect to.
		String host = args[0];
		int port = Integer.parseInt(args[1]);

		// these is the service name.
		String ServiceName = args[2];

		System.out.println("We lookup for" + ServiceName);

		// locate.
		RegistryInterface sr = LocateSimpleRegistry.getRegistry(host, port);

		System.out.println("located." + sr + "/n");

		if (sr != null) {
			// lookup.
			RemoteObjectRef ror = sr.lookup(ServiceName);

			if (ror != null) {
				System.out.println(ror.toString());
			} else {
				System.out.println("The service is bound to no remote object.");
			}
		} else {
			System.out.println("No registry found.");
		}

	}
}
