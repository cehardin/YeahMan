package cehardin.yeahman.impl;

import cehardin.yeahman.Address;
import cehardin.yeahman.Behavior;
import cehardin.yeahman.ActorCreator;
import cehardin.yeahman.ActorDoesNotExistException;
import cehardin.yeahman.ActorFactory;
import cehardin.yeahman.Message;
import cehardin.yeahman.MessageSender;
import cehardin.yeahman.State;
import cehardin.yeahman.ActorTypeDoesNotExistException;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Chad
 */
public class Engine implements ActorCreator, MessageSender {
    private final Map<UUIDAddress, ActorInstance> actorInstances;
    private final ConcurrentMap<String, ActorFactory> actorFactories;
    private final Executor executor;
    
    public Engine(final Executor executor) {
        this.executor = executor;
        actorInstances = Collections.synchronizedMap(new WeakHashMap<UUIDAddress, ActorInstance>());
        actorFactories = new ConcurrentHashMap<String, ActorFactory>();
    }
    
    public Address createActor(final String type) {
        final ActorFactory factory = actorFactories.get(type);
        
        if(factory != null) {
            final State state = factory.createState();
            final Behavior behavior = factory.createBehavior();
            final Lock lock = new ReentrantLock();
            final ActorInstance actor = new ActorInstance(state, behavior, lock);
            final UUIDAddress address = new UUIDAddress();
            
            actorInstances.put(address, actor);
            return address;
        }
        
        throw new ActorTypeDoesNotExistException(type);
    }
    
    public boolean addActorFactory(final String type, final ActorFactory actorFactory) {
        return actorFactories.put(type, actorFactory) != null;
    }

    public void sendMessage(final Address address, final Message message) {
        final ActorInstance actor = actorInstances.get(UUIDAddress.class.cast(address));
        
        if(actor != null) {
            final Behavior.Context behaviorContext = new ActorBehaviorContextImpl(address, actor.getState(), message.clone(), this, this);
            final ActorProcessor actorProcessor = new ActorProcessor(actor, behaviorContext);
            
            executor.execute(actorProcessor);
        }
        else {
            throw new ActorDoesNotExistException(address.toString());
        }
    }
    
    private static class ActorProcessor implements Runnable {
        private final ActorInstance actor;
        private final Behavior.Context behaviorContext;

        public ActorProcessor(final ActorInstance actor, final Behavior.Context behaviorContext) {
            this.actor = actor;
            this.behaviorContext = behaviorContext;
        }

        public void run() {
            final Lock lock = actor.getLock();
            final Behavior behavior = actor.getBehavior();
            
            if(lock.tryLock()) {
                try {
                    behavior.behave(behaviorContext);
                }
                finally {
                    lock.unlock();
                }
            }
        }
    }
}
