package cehardin.yeahman.impl;

import cehardin.yeahman.ActorBehavior;
import cehardin.yeahman.ActorState;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author Chad
 */
public class Actor {
    private final ActorState state;
    private final ActorBehavior behavior;
    private final Lock lock;

    public Actor(ActorState state, ActorBehavior behavior, Lock lock) {
        this.state = state;
        this.behavior = behavior;
        this.lock = lock;
    }

    public ActorState getState() {
        return state;
    }

    public ActorBehavior getBehavior() {
        return behavior;
    }

    public Lock getLock() {
        return lock;
    }
    
    
}
