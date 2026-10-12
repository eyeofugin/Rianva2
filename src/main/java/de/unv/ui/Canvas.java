package de.unv.ui;


import de.unv.framework.Game;

public class Canvas extends GUIBase {
    public Canvas() {
        super(0, 0, Game.X, Game.Y);
    }

    protected void paint(GUIBase guiBase) {
        fillWithGraphicsSize(this.pixels, this.width, guiBase.absX(), guiBase.absY(), guiBase.width, guiBase.height, guiBase.pixels);
    }

    protected void fillWithGraphicsSize(
            int[] target, int targetWidth, int xf, int yf, int w, int h, int[] graphics) {
        int xu = xf + w - 1;
        int yu = yf + h - 1;
        fillWithGraphics(target, targetWidth, xf, xu, yf, yu, graphics, Color.VOID);
    }

    public static void fillWithGraphics(
            int[] target,
            int targetWidth,
            int xfrom,
            int xuntil,
            int yfrom,
            int yuntil,
            int[] graphics,
            Color backgroundColor) {

        if (graphics == null) {
            return;
        }
        int index = 0;
        for (int i = yfrom; i <= yuntil; i++) {
            for (int j = xfrom; j <= xuntil; j++) {

                if (index >= graphics.length) {
                    continue;
                }
                if (j + i * targetWidth >= target.length) {
                    continue;
                }
                if (graphics[index] != Color.VOID.VALUE) {
                    int color = graphics[index];
                    int arrayindex = j + i * targetWidth;
                    if (arrayindex < target.length && arrayindex >= 0) target[arrayindex] = color;
                } else if (!backgroundColor.equals(Color.VOID)) {
                    target[j + i * targetWidth] = backgroundColor.VALUE;
                }
                index++;
            }
        }
    }
}