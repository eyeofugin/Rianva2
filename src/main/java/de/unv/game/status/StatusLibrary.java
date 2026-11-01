package de.unv.game.status;

import de.unv.connector.Subscriber;
import de.unv.connector.Subscription;
import de.unv.utils.FileWalker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusLibrary {
    private static Map<String, String> effectSubscriptions = new HashMap<>();

    public static void init() {
        effectSubscriptions = FileWalker.loadJsonMap("data/effectSubscriptions.json");
    }
    public static Status getStatus(String effectName) {
        return FileWalker.mapJson(Status.class, effectSubscriptions.get(effectName));
    }

}
