package io.zentae.snake.engine.listener;

import io.zentae.snake.engine.controller.snake.SnakeController;
import io.zentae.snake.engine.entity.Location;
import io.zentae.snake.engine.event.EventBus;
import io.zentae.snake.engine.event.snake.SnakeGrowEvent;

public class SnakeGrowListener extends Listener<SnakeGrowEvent> {

    public SnakeGrowListener() {
        super(SnakeGrowEvent.class);
        EventBus.subscribe(SnakeGrowEvent.class, this);
    }

    @Override
    protected void onListen(SnakeGrowEvent event) {
        // retrieve the snake.
        SnakeController snake = event.getSnake();
        // retrieve tail's location.
        Location tail = snake.getSnake().getTail();
        // create a new location.
        tail = new Location(tail.getX(), tail.getY());
        // make the player grow.
        snake.grow(tail);
    }
}
