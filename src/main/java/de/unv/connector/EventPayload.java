package de.unv.connector;

import de.unv.game.status.Status;
import de.unv.game.entities.Hero;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EventPayload {
    private Hero target;
    private Hero caster;
    private Status status;


}
