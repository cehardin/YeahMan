package cehardin.yeahman;

/**
 *
 * @author Chad
 */
public interface Behavior<S extends State, M extends Message> {

    public interface Context<S extends State, M extends Message> {

        Address getAddress();

        S getState();

        M getMessage();

        MessageSender getMessageSender();

        ActorCreator getCreator();

    }

    void behave(Context<S, M> context);
}
