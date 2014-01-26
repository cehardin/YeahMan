package cehardin.yeahman.impl;

import cehardin.yeahman.Address;
import cehardin.yeahman.Behavior;
import cehardin.yeahman.ActorCreator;
import cehardin.yeahman.Message;
import cehardin.yeahman.MessageSender;
import cehardin.yeahman.State;

/**
 *
 * @author Chad
 */
public class ActorBehaviorContextImpl<S extends State, M extends Message> implements Behavior.Context<S,M> {
    private final Address address;
    private final S state;
    private final M message;
    private final MessageSender messageSender;
    private final ActorCreator creator;

    public ActorBehaviorContextImpl(Address address, S state, M message, MessageSender messageSender, ActorCreator creator) {
        this.address = address;
        this.state = state;
        this.message = message;
        this.messageSender = messageSender;
        this.creator = creator;
    }
    
    public Address getAddress() {
        return address;
    }

    public S getState() {
        return state;
    }

    public M getMessage() {
        return message;
    }

    public MessageSender getMessageSender() {
        return messageSender;
    }

    public ActorCreator getCreator() {
        return creator;
    }    
}
