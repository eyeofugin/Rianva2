package de.unv.ui.animation;

import de.unv.ui.base.GUIBase;
import de.unv.ui.base.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Animator extends GUIBase {

  public Map<String, Sprite> animations = new HashMap<>();
  public String currentAnim;
  private String defaultAnim = "idle";
  public boolean onLoop;
  public int animationCounter = 0;
  public int width;
  public int height;

  public List<Sprite> effects = new ArrayList<>();
  public boolean waitFor;

  public Animator(Group parent) {
    super(parent);
  }

  public Animator(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  public void setupSprites(Map<String, Sprite> sprites) {
    sprites.forEach((name, sprite) -> {
      this.animations.put(name, sprite.copy());
    });
    currentAnim = defaultAnim;
    onLoop = true;
  }

  @Override
  public void render() {
    animate();
    renderPixels();
  }

  private void animate() {
    Sprite anim = animations.get(currentAnim);
    pixels = anim.getImage(animationCounter);
    if (animationCounter == anim.length) {
      animationCounter = 0;
      if (waitFor) {
        waitFor = false;
      }
      if (!onLoop) {
        currentAnim = defaultAnim;
        onLoop = true;
      }
    }
    animationCounter++;
  }

  public void playAnimation(String animName, boolean waitFor) {
    if (this.animations.containsKey(animName)) {
      currentAnim = animName;
      animationCounter = 0;
      this.waitFor = waitFor;
      onLoop = false;
    }
  }

  public void setDefaultAnim(String defaultAnim) {
    this.defaultAnim = defaultAnim;
    this.pixels = this.animations.get(this.defaultAnim).getImage(0);
  }
}
