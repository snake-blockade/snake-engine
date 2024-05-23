package io.zentae.snake.engine.handler;

import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class OneVersusOneGameHandler implements GameHandler {

    @Nullable
    @Override
    public Movement play(Optional<Movement> potentialInput) {
        return potentialInput.orElse(null);
    }

    @Override
    public void await(Consumer<Movement> consumer) {
        consumer.accept(Movement.NONE);
    }

    @Override
    public boolean needsNextMove() {
        return false;
    }
}
