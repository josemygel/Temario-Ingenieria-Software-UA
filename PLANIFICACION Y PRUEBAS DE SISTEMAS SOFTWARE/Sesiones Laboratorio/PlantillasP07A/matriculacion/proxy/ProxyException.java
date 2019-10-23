package ppss.matriculacion.proxy;

public class ProxyException extends Exception {

	private static final long serialVersionUID = 979885134968048050L;

	public ProxyException() {
	}

	public ProxyException(String message) {
		super(message);
	}

	public ProxyException(Throwable cause) {
		super(cause);
	}

	public ProxyException(String message, Throwable cause) {
		super(message, cause);
	}

}
