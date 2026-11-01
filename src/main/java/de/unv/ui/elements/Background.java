package de.unv.ui.elements;

import de.unv.ui.animation.SpriteLibrary;
import de.unv.ui.base.GUIBase;
import de.unv.ui.base.Group;

public class Background extends GUIBase {
    public Background(Group parent, String name) {
        super(parent);
        setImage(name);
        this.layer = 0;
    }
    private void setImage(String name) {
        this.pixels = SpriteLibrary.sprite(width, height, width, height, name, 0);
    }
}
