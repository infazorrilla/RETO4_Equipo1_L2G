package soundbridge.database.exception;

public class NotFoundException extends Exception {
	

	private static final long serialVersionUID = -2956866611454672829L;

	public NotFoundException() {
		
	}
	
	public NotFoundException (String message) {
		super(message);
	}

}
