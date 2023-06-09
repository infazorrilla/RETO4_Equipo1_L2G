package soundbridge.database.exception;

/**
 * Defines the exception risen when an instance is not found on database.
 */
public class NotFoundException extends GenericDataBaseExceptionAbstract {

	private static final long serialVersionUID = 6295731313058703705L;

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

}
