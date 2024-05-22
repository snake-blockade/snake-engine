package io.zentae.snake.engine.factory.arena;

import io.zentae.snake.engine.entity.arena.Arena;
import io.zentae.snake.engine.entity.snake.Snake;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface ArenaFactory {

    /**
     * Creates an {@link Arena} instance.
     * @param length the {@link Arena}'s length.
     * @param width the {@link Arena}'s width.
     * @return an {@link Arena}.
     */
    Arena create(@Nonnull List<Snake> snakes, int length, int width);
}
