package message;

public class LocateMessage extends RMIMessage {
	public int challenge = 0;

	public int getChallenge() {
		return challenge;
	}

	public void setChallenge(int challenge) {
		this.challenge = challenge;
	}

	public LocateMessage() {
		super();
		super.setType(MessageType.LOCATE);
	}

	public LocateMessage(int challenge) {
		this();
		this.setChallenge(challenge);
	}

	private static final long serialVersionUID = 2836270289753731814L;

}
