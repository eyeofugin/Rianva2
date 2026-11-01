package de.unv.ui.animation;

import de.unv.ui.base.Color;
import de.unv.utils.FileWalker;
import de.unv.utils.SpriteUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {
  private static Map<String, String> spritesJson = new HashMap<>();
  private static Map<String, Sprite> sprites = new HashMap<>();
  private static Map<String, int[]> icons = new HashMap<>();
  public static Map<String, int[]> portraits = new HashMap<>();
  public static Map<String, int[]> skills = new HashMap<>();

  public static void init() {
    setUpSprites();
  }

  private static void setUpSprites() {
    Map<String, Map<String, SpriteDTO>> sprites = FileWalker.mapSpriteSet("data/sprites.json");
    if (sprites == null) {
      return;
    }
    sprites.forEach((entityName, spriteMap) -> {
      spriteMap.forEach((spriteName, dto) -> {
        if (dto.frameLengths() == null || dto.source() == null || dto.imageHeight() == 0 || dto.imageWidth() == 0) {
          return;
        }
        int[][] images = SpriteUtils.splitSpriteSheet(dto.frameLengths().length, dto.imageWidth(), dto.imageHeight(), dto.source() );
        Sprite sprite = new Sprite(spriteName, dto.frameLengths(), images);
        SpriteLibrary.sprites.put(entityName + "_" + spriteName, sprite);
      });

    });
  }
  public static Map<String, Sprite> getSpritesFor(String entity) {
    Map<String, Sprite> result = new HashMap<>();
    String prefix = entity + "_";

    sprites.forEach((key, value) -> {
      if (key.startsWith(prefix)) {
        result.put(key.substring(prefix.length()), value);
      }
    });
    return result;
  }
  public static Sprite getSprite(String entity, String sprite) {
    String key = entity + "_" + sprite;
    return sprites.get(key);
  }

  public static int[] sprite(int targetW, int targetH, int w, int h, String path, int flip) {
    int[] sprite = new int[targetW * targetH];
    try {
      URI uri = SpriteLibrary.class.getClassLoader().getResource(path).toURI();
      BufferedImage sheet = ImageIO.read(new File(uri));
      int[] pixels = new int[w * h];
      if (flip == 180) {
        sheet = SpriteUtils.flipH(sheet);
      }
      if (flip == 90) {
        sheet = SpriteUtils.flip90(sheet);
      }
      sheet.getRGB(0, 0, w, h, pixels, 0, w);
      return convert(pixels, targetW, targetH, w);
    } catch (Exception e) {
      System.out.println("Error loading sprite: " + path);
    }
    return sprite;
  }

  public static int[][] setupSprite(int amntFrames, int w, int h, String path, boolean flip) {
    int[][] sprite = new int[amntFrames][w * h];
    for (int i = 0; i < amntFrames; i++) {
      Arrays.fill(sprite[i], Color.BUFF_GREEN.VALUE);
    }
    try {
      URI uri = SpriteLibrary.class.getClassLoader().getResource(path).toURI();
      BufferedImage sheet = ImageIO.read(new File(uri));
      for (int i = 0; i < amntFrames; i++) {
        int[] pixels = new int[w * h];
        BufferedImage img = sheet.getSubimage(i * w, 0, w, h);
        if (flip) {
          img = SpriteUtils.flipH(img);
        }
        img.getRGB(0, 0, w, h, pixels, 0, w);
        sprite[i] = pixels;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sprite;
  }

  private static int[] convert(int[] source, int targetW, int targetH, int w) {
    int[] p = new int[targetH * targetW];
    int mult = targetW / w;

    for (int y = 0; y < targetH; y++) {
      for (int x = 0; x < targetW; x++) {
        p[x + y * targetW] = source[(x / mult) + (y / mult) * w];
      }
    }
    return p;
  }
}
