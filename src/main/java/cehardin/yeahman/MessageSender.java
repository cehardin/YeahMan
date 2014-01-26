package cehardin.yeahman;

/**
 *
 * @author Chad
 */
public interface MessageSender {
    void sendMessage(Address address, Message message) throws ActorDoesNotExistException;
}
