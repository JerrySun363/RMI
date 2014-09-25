package message;

/**
 * All available Message Types. Each is bound to one specific class.
 * 
 * @author Jerry
 * 
 */
public enum MessageType {
	// REQUEST
	LOOKUP(LookupMessage.class), REBIND(RebindMessage.class),
	// RESPONSE
	LOOKUP_RESPONSE(LookupResponse.class), REBIND_RESPONSE(RebindResponse.class),
	// Method Call
	METHOD(MethodCall.class);

	private Class<?> myclass = null;

	MessageType(Class<?> myclass) {
		this.myclass = myclass;
	}

	public Class<?> getMyClass() {
		return this.myclass;
	}

}
