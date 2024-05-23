package io.zentae.snake.engine.handle;

import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nonnull;

import java.util.Optional;

public interface GameHandler {

    @Nonnull
    Movement play(Optional<Movement> potentialInput);

    Movement await();

    /**
     * Tells whether, after the player played his move, the engine should wait for another move, supposed automatic or from another computer.
     * The <code>{@link DefaultGameHandler}</code>
     */
    boolean needsNextMove();

}
