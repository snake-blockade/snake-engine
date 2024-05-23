package io.zentae.snake.engine.handler;

import io.zentae.snake.engine.io.ProtocolCoordinator;
import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nonnull;

import java.util.Optional;
import java.util.function.Consumer;

public class LANHandler implements GameHandler {

    @Nonnull
    @Override
    public Movement play(Optional<Movement> potentialInput) {
        return potentialInput.orElse(null);
    }

    @Override
    public void await(Consumer<Movement> consumer) {
        ProtocolCoordinator.get().getOpponentMove(consumer);
    }

    @Override
    public boolean needsNextMove() {
        return true;
    }
}
