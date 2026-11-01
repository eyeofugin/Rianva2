package de.unv.connector;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.unv.connector.conditions.TargetHasEffectCondition;
import lombok.Setter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(
                value = TargetHasEffectCondition.class,
                name = "TARGET_HAS_EFFECT"
        )
})
public abstract class Condition {

    public abstract boolean isMet(EventPayload payload);
}
