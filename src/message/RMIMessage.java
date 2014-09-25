package message;

import java.io.Serializable;

/**
 * 
 * @author Jerry
 *
 */
public abstract class RMIMessage implements Serializable {
	private static final long serialVersionUID = -2172115075014240044L;
	private MessageType type;

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	
}
