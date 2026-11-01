package de.unv.connector;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.unv.connector.commands.BurnCommand;
import de.unv.connector.commands.MoveCommand;
import de.unv.connector.commands.StackDamageCommand;
import lombok.Setter;

@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({@JsonSubTypes.Type(
        value = MoveCommand.class,
        name = "MOVE"
), @JsonSubTypes.Type(
        value = BurnCommand.class,
        name = "BURN"
), @JsonSubTypes.Type(
        value = StackDamageCommand.class,
        name = "STACK_DAMAGE"
)})
public abstract class Command {
    protected Subscription<?,?,?> subscription;
    public abstract void perform(EventPayload payload);
}
