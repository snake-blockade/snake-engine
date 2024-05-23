package io.zentae.snake.engine.controller.snake;

import io.zentae.snake.engine.entity.snake.Snake;
import io.zentae.snake.engine.movement.Movement;
import io.zentae.snake.engine.entity.Location;
import jakarta.annotation.Nonnull;

public interface SnakeController {

    /**
     * Make the controlled {@link Snake} move.
     */
    void move(Movement movement);

    /**
     * Predict the next {@link Movement}.
     * @param movement the {@link Movement} to predict.
     * @return the {@link Snake}'s head {@link Location} at the predicted location.
     */
    Location predict(Movement movement);

    /**
     * Make the snake grow.
     */
    void grow(Location location);

    /**
     * Make the snake decrease in size.
     */
    void decrease();

    /**
     * @return the {@link Snake} controlled.
     */
    @Nonnull
    Snake getSnake();
}
