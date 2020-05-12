package com.center.utilitize;

public class SqlExecuteException extends Exception {
	private static final long serialVersionUID = 1L;

	public SqlExecuteException() {
	}

	public SqlExecuteException(String message) {
		super(message);
	}

	public SqlExecuteException(Throwable cause) {
		super(cause);
	}

	public SqlExecuteException(String message, Throwable cause) {
		super(message, cause);
	}

	public SqlExecuteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
