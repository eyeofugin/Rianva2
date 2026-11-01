package de.unv.ui.animation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sprite {
  public Map<Integer, int[]> images = new HashMap<>();
  public int length;
  public String name = "";

  public Sprite(Map<Integer, int[]> images, int length, String name) {
    this.images = images;
    this.length = length;
    this.name = name;
  }

  public Sprite(String name, int[] ms, int[][] sprite) {
    if (ms.length == sprite.length) {
      for (int i = 0; i < ms.length; i++) {
        images.put(ms[i], sprite[i]);
      }
    }
    this.length = ms[ms.length - 1];
    this.name = name;
  }

  public int[] getImage(int counter) {
    List<Integer> sortedKeys = images.keySet().stream().sorted().toList();
    for (Integer i : sortedKeys) {
      if (counter <= i) {
        return images.get(i);
      }
    }
    return new int[0];
  }

  public Sprite copy() {
    Map<Integer, int[]> newImages = new HashMap<>();
    this.images.forEach((key, value) -> {
      newImages.put(key, value.clone());
    });
    return new Sprite(newImages, length, name);
  }
}
