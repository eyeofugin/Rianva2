package de.unv.game.states;

import de.unv.framework.Game;
import de.unv.ui.Group;

public abstract class State extends Group {

    int id;
    int previousId;
    boolean finished = false;
    boolean close = false;
    boolean goBack = false;
    boolean started = false;
    public final Memory memory;
    public int nextState;

    public State(Memory memory) {
        super(Game.X, Game.Y);
        this.memory = memory;
    }

    public abstract void start();

    public abstract void render();

    public abstract void finish();
}
