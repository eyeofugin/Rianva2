package de.unv.ui;

import de.unv.framework.Renderer;
import de.unv.utils.CollectionUtils;

import java.util.Arrays;

public class GUIBase extends Group {

    public int width, height;
    public int[] pixels;

    public GUIBase(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
    }

    @Override
    public void render(int frame) {
        renderPixels();
        renderChildren(frame);
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