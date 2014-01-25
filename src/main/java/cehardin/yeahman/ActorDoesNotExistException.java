package cehardin.yeahman;

/**
 *
 * @author Chad
 */
public class ActorDoesNotExistException extends RuntimeException {

    /**
     * Creates a new instance of <code>ActorDoesNotExistException</code> without
     * detail message.
     */
    public ActorDoesNotExistException() {
    }

    /**
     * Constructs an instance of <code>ActorDoesNotExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ActorDoesNotExistException(String msg) {
        super(msg);
    }
}
