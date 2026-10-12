package de.unv.framework;


import de.unv.ui.Canvas;
import de.unv.ui.Group;
import de.unv.utils.SpriteUtils;

public class Renderer {
    public static Canvas canvas;

    public static int[] render(Group group, int frame) {
        renderObject(group, frame);
        return SpriteUtils.upscale(canvas.pixels, Game.X, Game.Y, Game.multiplier);
    }
    public static void renderObject(Group group, int frame ) {
        group.render(frame);
    }
}

