package de.unv.game.states;

import de.unv.framework.Game;
import de.unv.ui.base.Group;

public abstract class State extends Group {

    int id;
    int previousId;
    boolean finished = false;
    boolean close = false;
    boolean goBack = false;
    boolean started = false;
    public int nextState;

    public State() {
        super(0, 0, Game.X, Game.Y);
    }

    public abstract void start();

    public abstract void finish();
}
