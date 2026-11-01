package de.unv.connector;

import java.util.*;

public final class Connector {

    private final static Map<Class<? extends Event>, List<Consumer<EventPayload>>> listeners =
            new HashMap<>();

    @FunctionalInterface
    public interface Consumer<EventPayload> {
        void accept(EventPayload payload);
    }

    public static <E extends Event> void subscribe(
            Class<E> eventType,
            Consumer<EventPayload> listener
    ) {
        listeners.computeIfAbsent(
                eventType, key -> new ArrayList<>()
        ).add(listener);
    }

    public static <E extends Event> void unsubscribe(
            Class<E> eventType,
            Consumer<EventPayload> listener
    ) {
        List<Consumer<EventPayload>> registered = listeners.get(eventType);

        if (registered != null) {
            registered.remove(listener);

            if (registered.isEmpty()) {
                listeners.remove(eventType);
            }
        }
    }

    public static <E extends Event> void publish(E event, EventPayload payload) {
        Objects.requireNonNull(event, "event");

        List<Consumer<EventPayload>> registered =
                listeners.get(event.getClass());

        if (registered == null) {
            return;
        }

        // Snapshot allows listeners to unsubscribe while handling.
        for (Consumer<EventPayload> listener : List.copyOf(registered)) {
            dispatch(listener, payload);
        }
    }

    @SuppressWarnings("unchecked")
    private static <E extends Event> void dispatch(
            Consumer<EventPayload> listener,
            EventPayload payload
    ) {
        listener.accept(payload);
    }

    public static void clear() {
        listeners.clear();
    }
}
