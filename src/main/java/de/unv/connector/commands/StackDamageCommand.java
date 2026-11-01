package de.unv.connector.commands;

import de.unv.connector.Command;
import de.unv.connector.EventPayload;
import lombok.Setter;

@Setter
public class StackDamageCommand extends Command {
    private int finalDamagePercentage;
    private int stackDamagePercentage;
    //private DamageType damageType;

    @Override
    public void perform(EventPayload payload) {
        if (payload.getStatus().increaseStack()) {
            if (payload.getStatus().isMaxStack()) {
                payload.getTarget().damagePercentage(finalDamagePercentage, "");
            } else {
                payload.getTarget().damagePercentage(stackDamagePercentage, "");
            }
        }
    }
}
