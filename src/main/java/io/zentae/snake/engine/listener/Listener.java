package io.zentae.snake.engine.listener;

import io.zentae.snake.engine.event.Event;

public abstract class Listener<T extends Event> {

    private final Class<T> tClass;

    public Listener(Class<T> tClass) {
        this.tClass = tClass;
    }

    public void on(Event event) {
        this.onListen(this.tClass.cast(event));
    }

    protected abstract void onListen(T event);
}
