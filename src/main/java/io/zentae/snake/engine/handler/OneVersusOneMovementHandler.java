package io.zentae.snake.engine.handler;

import io.zentae.snake.engine.controller.game.GameController;
import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class OneVersusOneMovementHandler implements MovementHandler {

    @Nullable
    @Override
    public Movement play(@Nonnull GameController arena, @Nonnull Optional<Movement> potentialInput) {
        return potentialInput.orElse(null);
    }

    @Override
    public void await(@Nonnull GameController arena, @Nonnull Consumer<Movement> consumer) {
        // should never be called as we do not need to wait for a move, as both moves comes from an input, which only triggers Movement#play.
        consumer.accept(Movement.NONE);
    }

    @Override
    public boolean needsNextMove() {
        return false;
    }
}
