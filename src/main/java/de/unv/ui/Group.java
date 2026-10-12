package de.unv.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group {
    public int x,y;
    public Group parent;
    public List<Group> children = new ArrayList<>();

    public Group(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Group child) {
        child.parent = this;
        this.children.add(child);
    }

    public void render( int frame ) {
        renderChildren( frame );
    }

    protected void renderChildren( int frame ) {
        this.children.removeIf( Objects::isNull );
        children.forEach( child -> child.render(frame) );
    }

    public int absX() {
        if (parent != null) {
            return parent.absX() + this.x;
        }
        return this.x;
    }
    public int x() {
        return x;
    }
    public int absY() {
        if (parent != null) {
            return parent.absY() + this.y;
        }
        return this.y;
    }
    public int y() {
        return y;
    }
    public void removeChild(Group child) {
        this.children.remove(child);
    }

    public void kill() {
        for (Group child : children) {
            child.kill();
            removeChild(child);
        }
    }

}