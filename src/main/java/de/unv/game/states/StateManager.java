package de.unv.game.states;


public class StateManager {

    public State currentState;

    public StateManager() {
        currentState = new DevState();
        currentState.start();
    }

}
