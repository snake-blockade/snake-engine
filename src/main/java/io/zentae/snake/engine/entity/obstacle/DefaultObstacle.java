package io.zentae.snake.engine.entity.obstacle;

import io.zentae.snake.engine.entity.Location;

import java.util.Collection;
import java.util.List;

public class DefaultObstacle implements Obstacle {

    private final Location segment;

    public DefaultObstacle(Location segment) {
        this.segment = segment;
    }

    @Override
    public Collection<Location> getSegments() {
        return List.of(this.segment);
    }
}
