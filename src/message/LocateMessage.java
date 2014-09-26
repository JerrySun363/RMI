package message;

public class LocateMessage extends RMIMessage {
	public LocateMessage() {
		super();
		super.setType(MessageType.LOCATE);
	}
	
	private static final long serialVersionUID = 2836270289753731814L;

}
