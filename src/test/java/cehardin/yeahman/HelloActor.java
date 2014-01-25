/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cehardin.yeahman;

/**
 *
 * @author Chad
 */
public class HelloActor {
    public static class State implements ActorState {
        public long n;
    }
    
    public static class Hello implements ActorMessage {
        private final ActorAddress from;

        public Hello(ActorAddress from) {
            this.from = from;
        }

        public ActorAddress getFrom() {
            return from;
        }

        public ActorMessage clone() {
            return this;    
        }
        
    }
    
    public static class Behavior implements ActorBehavior<State, Hello> {

        public void behave(ActorBehaviorContext<State, Hello> context) {
            System.out.printf("I'm %s and I just got a hello from %s%n", context.getAddress(), context.getMessage().getFrom());
            context.getState().n++;
            context.getMessageSender().sendMessage(context.getMessage().getFrom(), new Hello(context.getAddress()));
        }
        
    }
    
    public static class Factory implements ActorFactory<State, Hello> {

        public State createState() {
            return new State();
        }

        public ActorBehavior<State, Hello> createBehavior() {
            return new Behavior();
        }
        
    }
    
}
