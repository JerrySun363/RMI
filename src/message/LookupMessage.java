package message;

/**
 * This Message specifies itself as a LookupMessage
 * 
 * @author Jerry
 *
 */
public class LookupMessage extends RMIMessage {
	private static final long serialVersionUID = 7867001998290322322L;
	String serviceName;
	public LookupMessage(String serviceName){
		super();
		super.setType(MessageType.LOOKUP);
		this.serviceName = serviceName;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	
}
