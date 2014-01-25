package cehardin.yeahman;

/**
 *
 * @author Chad
 */
public interface ActorCreator {
    ActorAddress createActor(String type) throws ActorTypeDoesNotExistException;
}
