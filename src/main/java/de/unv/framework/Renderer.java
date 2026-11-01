package de.unv.framework;


import de.unv.ui.base.Canvas;
import de.unv.ui.base.Color;
import de.unv.ui.base.Group;
import de.unv.utils.SpriteUtils;

public class Renderer {
    public static Canvas canvas = new Canvas();

    public static int[] render(Group group) {
        canvas.background(Color.WHITE.VALUE);
        renderObject(group);
        return SpriteUtils.upscale(canvas.pixels, Game.X, Game.Y, Game.multiplier);
    }
    public static void renderObject(Group group) {
        group.render();
    }
}

