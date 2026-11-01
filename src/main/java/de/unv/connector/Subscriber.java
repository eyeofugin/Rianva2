package de.unv.connector;

import de.unv.ui.base.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Subscriber extends Group {
    List<Subscription<Event,Condition,Command>> subscriptions = new ArrayList<>();

    public void subscribe() {
        subscriptions.forEach(Subscription::subscribe);
    }
    public void unsubscribe() {
        subscriptions.forEach(Subscription::unsubscribe);
    }
}
