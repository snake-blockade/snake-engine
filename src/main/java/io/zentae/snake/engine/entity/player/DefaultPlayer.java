package io.zentae.snake.engine.entity.player;

import io.zentae.snake.engine.controller.snake.SnakeController;
import jakarta.annotation.Nonnull;

public class DefaultPlayer implements Player {

    // the snake controller.
    private final SnakeController controller;

    public DefaultPlayer(SnakeController controller) {
        this.controller = controller;
    }

    @Override
    @Nonnull
    public SnakeController getController() {
        return this.controller;
    }
}
