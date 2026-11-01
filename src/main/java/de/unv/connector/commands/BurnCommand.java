package de.unv.connector.commands;

import de.unv.connector.Command;
import de.unv.connector.EventPayload;
import lombok.Setter;

@Setter
public class BurnCommand extends Command {
    private int maxStack;
    private int currentStack;
    private int finalDamagePercentage;
    private int stackDamagePercentage;
    //private DamageType damageType;

    @Override
    public void perform(EventPayload payload) {
        currentStack++;
        if (currentStack == maxStack) {
            payload.getTarget().damagePercentage(finalDamagePercentage, "");
//            subscription.setFulfilled(true);
        } else {
            payload.getTarget().damagePercentage(stackDamagePercentage, "");
        }
        payload.getStatus().setStack(currentStack);
    }
}
