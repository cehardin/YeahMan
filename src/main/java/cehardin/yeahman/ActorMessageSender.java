package cehardin.yeahman;

/**
 *
 * @author Chad
 */
public interface ActorMessageSender {
    void sendMessage(ActorAddress address, ActorMessage message) throws ActorDoesNotExistException;
}
