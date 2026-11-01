package de.unv.connector;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.unv.connector.events.TargetPaysEnergyEvent;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(
                value = TargetPaysEnergyEvent.class,
                name = "TARGET_PAYS_ENERGY"
        )
})
public abstract class Event {

}
