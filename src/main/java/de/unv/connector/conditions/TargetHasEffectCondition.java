package de.unv.connector.conditions;

import de.unv.connector.Condition;
import de.unv.connector.EventPayload;

public class TargetHasEffectCondition extends Condition {
    public String effectName;
    @Override
    public boolean isMet(EventPayload payload) {
        return false;
    }
}
