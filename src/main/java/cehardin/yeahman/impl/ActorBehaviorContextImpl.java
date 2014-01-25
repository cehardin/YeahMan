/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cehardin.yeahman.impl;

import cehardin.yeahman.ActorAddress;
import cehardin.yeahman.ActorBehaviorContext;
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

    public ActorBehaviorContextImpl(ActorAddress address, S state, M message, ActorMessageSender messageSender) {
        this.address = address;
        this.state = state;
        this.message = message;
        this.messageSender = messageSender;
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
    
    
}
