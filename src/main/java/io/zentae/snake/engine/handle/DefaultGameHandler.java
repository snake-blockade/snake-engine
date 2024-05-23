package io.zentae.snake.engine.handle;

import io.zentae.snake.engine.movement.Movement;

import java.util.Optional;

public class DefaultGameHandler implements GameHandler {

    @Override
    public Movement play(Optional<Movement> potentialInput) {
        return potentialInput.orElseGet(null);
    }

    @Override
    public Movement await() {
        return Movement.NONE;
    }

    @Override
    public boolean needsNextMove() {
        return false;
    }
}
