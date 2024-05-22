package io.zentae.snake.engine.event.snake;

import io.zentae.snake.engine.controller.game.GameController;
import io.zentae.snake.engine.controller.snake.SnakeController;
import io.zentae.snake.engine.entity.arena.Arena;
import io.zentae.snake.engine.entity.snake.Snake;
import io.zentae.snake.engine.event.GameEvent;

public class SnakeGrowEvent extends GameEvent {

    private final Arena arena;
    private final SnakeController snake;

    public SnakeGrowEvent(GameController game, SnakeController snake) {
        super(game);
        this.snake = snake;
        this.arena = game.getGame().getArena();
    }

    public Arena getArena() {
        return this.arena;
    }

    public SnakeController getSnake() { return this.snake; }
}
