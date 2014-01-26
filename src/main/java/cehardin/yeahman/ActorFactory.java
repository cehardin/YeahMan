package cehardin.yeahman;

/**
 *
 * @author Chad
 */
public interface ActorFactory<S extends State, M extends Message> {
    S createState();
    Behavior<S, M> createBehavior();
}
