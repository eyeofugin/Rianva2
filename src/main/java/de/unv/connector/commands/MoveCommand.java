package de.unv.connector.commands;

import de.unv.connector.Command;
import de.unv.connector.EventPayload;
import de.unv.connector.Subscriber;
import lombok.Data;

@Data
public class MoveCommand extends Command {

    private int direction;

    @Override
    public void perform(EventPayload payload) {
        //payload.getTarget().move(direction);
    }
}
