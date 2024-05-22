package io.zentae.snake.engine.entity.fruit;

import io.zentae.snake.engine.entity.Location;

import java.util.Collection;
import java.util.List;

public class DefaultFruit implements Fruit {

    // the fruit's location.
    private final Location segment;

    public DefaultFruit(Location segment) {
        this.segment = segment;
    }

    @Override
    public Collection<Location> getSegments() {
        return List.of(segment);
    }
}
