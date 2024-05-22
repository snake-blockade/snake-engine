package io.zentae.snake.engine.controller.snake;

import io.zentae.snake.engine.entity.Location;
import io.zentae.snake.engine.entity.snake.Snake;
import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nonnull;

public class DefaultSnakeController implements SnakeController {

    // the actual snake to control.
    private final Snake snake;

    public DefaultSnakeController(@Nonnull Snake snake) {
        this.snake = snake;
    }

    @Override
    public void move(@Nonnull Movement movement) {
        // loop through each body part.
        for(int i = snake.getBody().size() - 1; i > 0; i--) {
            // retrieve the next body part.
            Location next = snake.getBody().get(i - 1);
            // retrieve the current body part.
            Location current = snake.getBody().get(i);
            // set the current body part's location to the previous one.
            current.set(next.getX(), next.getY());
        }
        // the move implementation.
        // make the head move.
        snake.getHead().add(movement);
    }

    @Override
    public Location predict(Movement movement) {
        // retrieve the head.
        Location head = snake.getHead();
        // predict the future.
        Location prediction = new Location(head.getX(), head.getY());
        // add the movement.
        prediction.add(movement);
        // return the prediction.
        return prediction;
    }

    @Override
    public void grow(Location location) {
        this.snake.getBody().add(location);
    }

    @Override
    public void decrease() {
        this.snake.getBody().remove(this.snake.getTail());
    }

    @Nonnull
    @Override
    public Snake getSnake() {
        return this.snake;
    }
}
