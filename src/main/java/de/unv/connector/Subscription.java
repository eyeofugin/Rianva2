package de.unv.connector;

import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class Subscription<
        E extends Event,
        C extends Condition,
        M extends Command> {

    protected E event;
    protected List<C> conditionList;
    protected M command;
//    protected Subscriber subscriber;
//    protected boolean fulfilled = false;

    public Subscription() {

    }
    public Subscription(E event, List<C> conditionList, M command) {
        this.event = event;
        this.conditionList = conditionList;
        this.command = command;
        this.command.setSubscription(this);
    }

    public void subscribe() {
        Connector.subscribe(event.getClass(),this::perform );
    }

    public void unsubscribe() {
        Connector.unsubscribe(event.getClass(), this::perform);
    }

    public void perform(EventPayload payload) {
        if (conditionList.stream().allMatch(c -> c.isMet(payload))) {
            command.perform(payload);
        }
    }
}
