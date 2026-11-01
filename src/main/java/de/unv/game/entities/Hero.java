package de.unv.game.entities;

import de.unv.ui.base.Group;
import de.unv.ui.animation.Animator;
import de.unv.ui.animation.SpriteLibrary;

public class Hero extends Group {

    private HeroData data;

    public Hero() {
        super(10,10,64,64);
        Animator animator = new Animator(this);
        animator.setupSprites(SpriteLibrary.getSpritesFor("CinderSprite"));
        add(animator);
    }

    public void damage(int amount, String damageType) {

    }

    public void damagePercentage(int percentage, String damageType) {

    }
}
