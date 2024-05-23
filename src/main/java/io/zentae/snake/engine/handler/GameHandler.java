package io.zentae.snake.engine.handler;

import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nonnull;

import java.util.Optional;
import java.util.function.Consumer;

public interface GameHandler {

    @Nonnull
    Movement play(Optional<Movement> potentialInput);

    void await(Consumer<Movement> consumer);

    /**
     * Tells whether, after the player played his move, the engine should wait for another move, supposed automatic or from another computer.
     */
    boolean needsNextMove();

}
