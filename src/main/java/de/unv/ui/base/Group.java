package de.unv.ui.base;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.unv.connector.Subscriber;
import de.unv.game.status.Status;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(
            value = Subscriber.class,
            name = "SUBSCRIBER"
        ),
        @JsonSubTypes.Type(
            value = Status.class,
            name = "STATUS"
)
})
public class Group {
    public String id;
    public int layer = 1;
    public int x,y;
    public int width, height;
    public Group parent;
    public List<Group> children = new ArrayList<>();
    public boolean active;
    public boolean alive = true;

    public Group() {

    }
    public Group(Group parent) {
        this.parent = parent;
        this.x = 0;
        this.y = 0;
        this.width = parent.width;
        this.height = parent.height;
    }

    public Group(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void add(Group child) {
        child.parent = this;
        children.add(child);
    }

    public void update(int frame) {
        updateChildren(frame);
    }
    protected void updateChildren(int frame) {
        children.removeIf( Objects::isNull );
        children.stream()
                .filter(Group::active)
                .filter( c-> c.alive )
                .forEach( c -> c.update(frame));
    }
    public void render() {
        renderChildren();
    }

    protected void renderChildren() {
        children.removeIf( Objects::isNull );
        children.removeIf( c-> !c.alive );
        children.sort(Comparator.comparingInt(a -> a.layer));
        children.forEach(Group::render);
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
    public void removeChild(String id) {
        this.children.removeIf(c->id.equals(c.id));
    }
    public Group getChild(String id) {
        return this.children.stream().filter(c->id.equals(c.id)).findFirst().orElse(null);
    }

    public void kill() {
        for (Group child : children) {
            child.kill();
            removeChild(child);
        }
        this.alive = false;
    }

    public void transform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public boolean active() {
        return active;
    }
    public void activate() {
        this.active = true;
    }
}