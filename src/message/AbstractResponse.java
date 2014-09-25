package message;

public abstract class AbstractResponse extends RMIMessage {
	
	private static final long serialVersionUID = -8814189361216742729L;
	private boolean success = true;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
