package io.zentae.snake.engine.entity.player;

import io.zentae.snake.engine.controller.snake.SnakeController;
import jakarta.annotation.Nonnull;

public interface Player {

    /**
     * @return the {@link Player}'s {@link SnakeController} associated to it.
     */
    @Nonnull
    SnakeController getController();
}
