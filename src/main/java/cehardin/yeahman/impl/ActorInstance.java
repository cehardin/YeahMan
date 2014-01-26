package cehardin.yeahman.impl;

import cehardin.yeahman.Behavior;
import cehardin.yeahman.State;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author Chad
 */
public class ActorInstance {
    private final State state;
    private final Behavior behavior;
    private final Lock lock;

    public ActorInstance(State state, Behavior behavior, Lock lock) {
        this.state = state;
        this.behavior = behavior;
        this.lock = lock;
    }

    public State getState() {
        return state;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public Lock getLock() {
        return lock;
    }
    
    
}
