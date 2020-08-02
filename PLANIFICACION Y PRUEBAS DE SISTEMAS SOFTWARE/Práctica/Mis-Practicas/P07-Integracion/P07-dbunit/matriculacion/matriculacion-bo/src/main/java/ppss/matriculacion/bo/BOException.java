package ppss.matriculacion.bo;

public class BOException extends Exception {

	private static final long serialVersionUID = 3439057538117880026L;

	public BOException() {
		super();
	}

	public BOException(String message, Throwable cause) {
		super(message, cause);
	}

	public BOException(String message) {
		super(message);
	}

	public BOException(Throwable cause) {
		super(cause);
	}

}
