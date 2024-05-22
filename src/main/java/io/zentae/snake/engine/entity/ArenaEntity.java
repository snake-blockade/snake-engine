package io.zentae.snake.engine.entity;

import java.util.Collection;

public interface ArenaEntity {

    Collection<Location> getSegments();
    /**
     * @return the actual class in order to identify it.
     */
    Class<?> getType();
}
