package io.zentae.snake.engine.event;

import io.zentae.snake.engine.listener.Listener;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventBus {

    // the bus buffer.
    private static final Map<Class<? extends Event>, Collection<Listener<? extends Event>>> EVENT_BUS = new HashMap<>();

    public static void dispatch(@Nonnull Event event) {
        // try to retrieve consumer.
        Collection<Listener<? extends Event>> listeners = EVENT_BUS.get(event.getClass());
        // check if there's no consumer.
        if(listeners == null)
            return;
        listeners.forEach(listener -> listener.on(event));
    }

    public static <T extends Event> void subscribe(@Nonnull Class<T> tClass, @Nonnull Listener<T> listener) {
        EVENT_BUS.compute(tClass, (k, v) -> {
            v = v == null ? new ArrayList<>() : v;
            v.add(listener);
            return v;
        });
    }
}
