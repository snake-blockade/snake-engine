package io.zentae.snake.engine.handler;

import io.zentae.snake.engine.algorithm.SnakeAlgorithm;
import io.zentae.snake.engine.controller.game.GameController;
import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class AIMovementHandler implements MovementHandler {

    private final SnakeAlgorithm algorithm = new SnakeAlgorithm();

    @Nullable
    @Override
    public Movement play(@Nonnull GameController game, @Nonnull Optional<Movement> potentialInput) {
        return potentialInput.orElse(null);
    }

    @Override
    public void await(@Nonnull GameController game, @Nonnull Consumer<Movement> consumer) {
        consumer.accept(algorithm.process(game));
    }

    @Override
    public boolean needsNextMove() {
        return true;
    }
}
