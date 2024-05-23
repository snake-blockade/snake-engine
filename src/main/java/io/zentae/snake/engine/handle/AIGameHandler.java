package io.zentae.snake.engine.handle;

import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nullable;

import java.util.Optional;

public class AIGameHandler implements GameHandler {

    @Nullable
    @Override
    public Movement play(Optional<Movement> potentialInput) {
        return potentialInput.orElse(null);
    }

    @Override
    public Movement await() {
        // todo mettre le move décidé par l'IA ici.
        return Movement.UP;
    }

    @Override
    public boolean needsNextMove() {
        return true;
    }
}
