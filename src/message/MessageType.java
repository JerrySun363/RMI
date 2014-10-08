package message;

/**
 * All available Message Types. Each is bound to one specific class.
 * 
 * @author Jerry Sun
 * 
 */
public enum MessageType {
	// REQUEST
	LOOKUP,

	REBIND,

	UNBIND,

	BIND,
	// RESPONSE
	LOOKUP_RESPONSE,

	REBIND_RESPONSE,

	BIND_RESPONSE,

	UNBIND_RESPONSE,
	// Method Call
	METHOD,
	// TODO: Add method response
	// Method Response
	METHOD_RETURN,
	// Exception
	EXCEPTION,
	// Locate
	LOCATE, 
	
	LOCATE_RESPONSE;
}
