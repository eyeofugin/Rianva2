package de.unv.ui.animation;

import de.unv.ui.base.Color;
import de.unv.ui.base.GUIBase;

public class Effect extends GUIBase {

    int life;
    public Effect() {
        super(10, 100, 10, 10);
        background(Color.RED.VALUE);
        this.active = true;
        this.life = 300;
    }
    @Override
    public void update(int frame) {
        this.x += frame % 2 == 0 ? 1: 0;
        this.life--;
        if (life == 0) {
            kill();
        }
    }
}
