package io.zentae.snake.engine.factory.snake;

import io.zentae.snake.engine.entity.Location;
import io.zentae.snake.engine.entity.snake.Snake;
import jakarta.annotation.Nonnull;

public interface SnakeFactory {

    /**
     * Creates a {@link Snake} of size 2 at the given {@link Location}.
     * Notes that the head will spawn at the given {@link Location}.
     * @param location the {@link Snake}'s head {@link Location}.
     * @return a {@link Snake}.
     */
    Snake create(@Nonnull Location location);
    Snake create(int size);
}
