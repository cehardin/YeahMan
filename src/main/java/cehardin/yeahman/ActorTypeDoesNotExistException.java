package cehardin.yeahman;

/**
 *
 * @author Chad
 */
public class ActorTypeDoesNotExistException extends RuntimeException {

    /**
     * Creates a new instance of <code>ActorDoesNotExistException</code> without
     * detail message.
     */
    public ActorTypeDoesNotExistException() {
    }

    /**
     * Constructs an instance of <code>ActorDoesNotExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ActorTypeDoesNotExistException(String msg) {
        super(msg);
    }
}
