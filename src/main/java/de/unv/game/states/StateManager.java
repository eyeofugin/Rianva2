package de.unv.game.states;


public class StateManager {

    public State currentState;

    public StateManager() {
    }


    public void update() {
        State state = this.scenes.getFirst();
        if (state.nextState != 0) {
            State nextState = getNewState(state.nextState);
            if (state.close) {
                state.finish();
                this.scenes.removeFirst();
            }
            this.scenes.add(nextState);
        } else if (state.goBack) {
            state.finish();
            this.scenes.removeFirst();
        } else {
            state.update(frame);
        }
    }
}
