package framework.registry;

import java.io.IOException;

import exception.RemoteServiceException;
import framework.RemoteObjectRef;

public interface RegistryInterface {

	/**
	 * Returns the ROR (if found) or null (if else)
	 * 
	 * @param serviceName
	 * @return RemoteObjectRef
	 * @throws IOException
	 * @throws RemoteServiceException
	 */
	public abstract RemoteObjectRef lookup(String serviceName)
			throws RemoteServiceException;

	/**
	 * Binds the specified name to a remote object.
	 * 
	 * @param serviceName
	 *            - The name of the services.
	 * @param ROR
	 *            - A Remote Object Reference
	 * @throws RemoteServiceException
	 * 
	 */
	public abstract void bind(String serviceName, RemoteObjectRef ror)
			throws RemoteServiceException;

	/**
	 * Rebind a ROR.
	 * 
	 * @param serviceName
	 * @param ror
	 * @throws IOException
	 * @throws RemoteServiceException
	 */
	public abstract void rebind(String serviceName, RemoteObjectRef ror) throws RemoteServiceException;

	/**
	 * 
	 * @param serviceName
	 * @param ror
	 * @throws RemoteServiceException
	 */
	public abstract void unbind(String serviceName)
			throws RemoteServiceException;

}