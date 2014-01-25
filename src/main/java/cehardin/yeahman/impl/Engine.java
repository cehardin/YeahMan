package cehardin.yeahman.impl;

import cehardin.yeahman.ActorAddress;
import cehardin.yeahman.ActorBehavior;
import cehardin.yeahman.ActorBehaviorContext;
import cehardin.yeahman.ActorCreator;
import cehardin.yeahman.ActorDoesNotExistException;
import cehardin.yeahman.ActorFactory;
import cehardin.yeahman.ActorMessage;
import cehardin.yeahman.ActorMessageSender;
import cehardin.yeahman.ActorState;
import cehardin.yeahman.ActorTypeDoesNotExistException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Chad
 */
public class Engine implements ActorCreator, ActorMessageSender {
    private final ConcurrentMap<ActorAddress, Actor> actors = new ConcurrentHashMap<ActorAddress, Actor>();
    private final ConcurrentMap<String, ActorFactory> actorFactories = new ConcurrentHashMap<String, ActorFactory>();
    private final Executor executor;

    public Engine(final Executor executor) {
        this.executor = executor;
    }
    
    public ActorAddress createActor(final String type) {
        final ActorFactory factory = actorFactories.get(type);
        
        if(factory != null) {
            final ActorState state = factory.createState();
            final ActorBehavior behavior = factory.createBehavior();
            final Lock lock = new ReentrantLock();
            final Actor actor = new Actor(state, behavior, lock);
            final ActorAddress address = new ActorAddressImpl();
            
            actors.put(address, actor);
            return address;
        }
        
        throw new ActorTypeDoesNotExistException(type);
    }
    
    public boolean addActorFactory(final String type, final ActorFactory actorFactory) {
        return actorFactories.put(type, actorFactory) != null;
    }

    public void sendMessage(final ActorAddress address, final ActorMessage message) {
        final Actor actor = actors.get(address);
        
        if(actor != null) {
            final ActorBehaviorContext behaviorContext = new ActorBehaviorContextImpl(address, actor.getState(), message.clone(), this);
            final ActorProcessor actorProcessor = new ActorProcessor(actor, behaviorContext);
            
            executor.execute(actorProcessor);
        }
        else {
            throw new ActorDoesNotExistException(address.toString());
        }
    }
    
    private static class ActorProcessor implements Runnable {
        private final Actor actor;
        private final ActorBehaviorContext behaviorContext;

        public ActorProcessor(final Actor actor, final ActorBehaviorContext behaviorContext) {
            this.actor = actor;
            this.behaviorContext = behaviorContext;
        }

        public void run() {
            final Lock lock = actor.getLock();
            final ActorBehavior behavior = actor.getBehavior();
            
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
