package exception;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends Exception {
	
	public UserAlreadyExistException(String message) {
		super(message);
	}

	UserAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	UserAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	UserAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	UserAlreadyExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}