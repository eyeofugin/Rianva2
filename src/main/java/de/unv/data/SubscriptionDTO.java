package de.unv.data;

import de.unv.connector.Command;
import de.unv.connector.Condition;
import de.unv.connector.Event;

import java.util.List;

public class SubscriptionDTO<
        E extends Event,
        C extends Condition,
        M extends Command> {

    protected final E event;
    protected final List<C> conditionList;
    protected final M command;

    public SubscriptionDTO(E event, List<C> conditionList, M command) {
        this.event = event;
        this.conditionList = conditionList;
        this.command = command;
    }
}
