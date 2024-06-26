package io.zentae.snake.engine.handler;

import io.zentae.snake.engine.controller.game.GameController;
import io.zentae.snake.engine.io.ProtocolCoordinator;
import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class LANHandler implements MovementHandler {

    @Nullable
    @Override
    public Movement play(@Nonnull GameController arena, @Nonnull Optional<Movement> potentialInput) {
        return potentialInput.orElse(null);
    }

    @Override
    public void await(@Nonnull GameController arena, @Nonnull Consumer<Movement> consumer) {
        ProtocolCoordinator.get().getOpponentMove(consumer);
    }

    @Override
    public boolean needsNextMove() {
        return true;
    }
}
