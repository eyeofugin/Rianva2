package de.unv.ui.animation;


public record SpriteDTO(String source, int imageWidth, int imageHeight, int[] frameLengths) {

    public SpriteDTO copy() {
        return new SpriteDTO(source, imageWidth, imageHeight, frameLengths.clone());
    }
}
