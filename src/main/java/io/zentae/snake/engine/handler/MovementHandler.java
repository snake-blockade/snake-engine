package io.zentae.snake.engine.handler;

import io.zentae.snake.engine.controller.game.GameController;
import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public interface MovementHandler {

    @Nullable
    Movement play(@Nonnull GameController arena, @Nonnull Optional<Movement> potentialInput);

    void await(@Nonnull GameController arena, Consumer<Movement> consumer);

    /**
     * Tells whether, after the player played his move, the engine should wait for another move, supposed automatic or from another computer.
     */
    boolean needsNextMove();

}
