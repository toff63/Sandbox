package net.francesbagual.matandoif.highorderfunction.stringasfunction.lib;

public class OperationNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public OperationNotFoundException() {
		super();
	}

	public OperationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OperationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationNotFoundException(String message) {
		super(message);
	}

	public OperationNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
