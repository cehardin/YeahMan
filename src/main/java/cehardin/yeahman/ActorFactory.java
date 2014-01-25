package cehardin.yeahman;

/**
 *
 * @author Chad
 */
public interface ActorFactory<S extends ActorState, M extends ActorMessage> {
    S createState();
    ActorBehavior<S, M> createBehavior();
}
