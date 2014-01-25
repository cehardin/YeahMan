package cehardin.yeahman;

/**
 *
 * @author Chad
 */
public interface ActorBehaviorContext<S extends ActorState, M extends ActorMessage> {
    ActorAddress getAddress();
    S getState();
    M getMessage();
    ActorMessageSender getMessageSender();
    
}
