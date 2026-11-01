package de.unv.game.states;

import de.unv.connector.Subscription;
import de.unv.game.status.StatusLibrary;
import de.unv.game.status.Status;
import de.unv.game.entities.Hero;
import de.unv.ui.animation.Effect;
import de.unv.ui.elements.Background;
import de.unv.utils.FileWalker;

import java.util.Map;

public class DevState extends State {

    @Override
    public void start() {
        add(new Background(this, "scenes/scene01.png"));
        add(new Hero());
        add(new Effect());

        StatusLibrary.init();
        Map<String, String> subscriptionMap = FileWalker.loadJsonMap("data/effectSubscriptions.json");
        Subscription<?,?,?> subscription = FileWalker.mapJson(Subscription.class, subscriptionMap.get("Burn"));

        Status status = StatusLibrary.getStatus("Burning1");

        System.out.println("");

    }

    @Override
    public void finish() {

    }
}
