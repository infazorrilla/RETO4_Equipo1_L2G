package soundbridge.database.exception;

public abstract class GenericDataBaseExceptionAbstract extends Exception {

	private static final long serialVersionUID = -4870718908739209739L;

	public GenericDataBaseExceptionAbstract(String message) {
		super(message);
	}

	public GenericDataBaseExceptionAbstract(Throwable cause) {
		super(cause);
	}
}
