package cehardin.yeahman;

/**
 *
 * @author Chad
 */
public interface ActorBehavior<S extends ActorState, M extends ActorMessage> {
    void behave(ActorBehaviorContext<S,M> context);
}
