package cehardin.yeahman.impl;

import cehardin.yeahman.ActorAddress;
import cehardin.yeahman.ActorBehaviorContext;
import cehardin.yeahman.ActorCreator;
import cehardin.yeahman.ActorMessage;
import cehardin.yeahman.ActorMessageSender;
import cehardin.yeahman.ActorState;

/**
 *
 * @author Chad
 */
public class ActorBehaviorContextImpl<S extends ActorState, M extends ActorMessage> implements ActorBehaviorContext<S,M> {
    private final ActorAddress address;
    private final S state;
    private final M message;
    private final ActorMessageSender messageSender;
    private final ActorCreator creator;

    public ActorBehaviorContextImpl(ActorAddress address, S state, M message, ActorMessageSender messageSender, ActorCreator creator) {
        this.address = address;
        this.state = state;
        this.message = message;
        this.messageSender = messageSender;
        this.creator = creator;
    }
    
    public ActorAddress getAddress() {
        return address;
    }

    public S getState() {
        return state;
    }

    public M getMessage() {
        return message;
    }

    public ActorMessageSender getMessageSender() {
        return messageSender;
    }

    public ActorCreator getCreator() {
        return creator;
    }
    
    
    
}
