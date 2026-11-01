package de.unv.ui.base;

import de.unv.framework.Renderer;
import de.unv.utils.CollectionUtils;

import java.util.Arrays;

public class GUIBase extends Group {

    public int[] pixels;

    public GUIBase(Group parent) {
        super(parent);
    }

    public GUIBase(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.pixels = new int[width * height];
    }

    @Override
    public void render() {
        renderPixels();
        renderChildren();
    }

    public void renderPixels() {
        if (!CollectionUtils.isEmpty(this.pixels)) {
            Renderer.canvas.paint( this );
        }
    }

    public void background(int value) {
        Arrays.fill(pixels, value);
    }
}