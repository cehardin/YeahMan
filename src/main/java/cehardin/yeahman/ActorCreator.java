package cehardin.yeahman;

/**
 *
 * @author Chad
 */
public interface ActorCreator {
    Address createActor(String type) throws ActorTypeDoesNotExistException;
}
