package de.unv.game.status;

import de.unv.connector.Subscriber;
import de.unv.connector.Subscription;
import de.unv.ui.base.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Status extends Group {

    private String name;
    protected int stack;
    protected int maxStack;
    protected int turns;

    public Status() {

    }
    public Status(Group parent) {
        super(parent);
    }

    public void fill(String name) {
        this.name = name;
        Subscriber subscriber = new Subscriber();
//        List<Subscription<?,?,?>> subscriptionList = new ArrayList<>();
//        subscriber.setSubscriptions(subscriptionList);

    }

    public boolean increaseStack() {
        int _stack = this.stack;
        this.stack = Math.min(maxStack, stack + 1);
        return _stack != this.stack;
    }

    public boolean decreaseStack() {
        int _stack = this.stack;
        this.stack = Math.max(0, stack - 1);
        return _stack != this.stack;
    }

    public boolean isMaxStack() {
        return this.stack == this.maxStack;
    }
}
