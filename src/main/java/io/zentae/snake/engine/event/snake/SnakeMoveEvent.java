package io.zentae.snake.engine.event.snake;

import io.zentae.snake.engine.controller.game.GameController;
import io.zentae.snake.engine.entity.arena.Arena;
import io.zentae.snake.engine.entity.snake.Snake;
import io.zentae.snake.engine.event.GameEvent;

public class SnakeMoveEvent extends GameEvent {

    private final Snake snake;
    private final Arena arena;

    public SnakeMoveEvent(GameController game, Snake snake) {
        super(game);
        this.snake = snake;
        this.arena = game.getGame().getArena();
    }

    public Snake getSnake() {
        return this.snake;
    }

    public Arena getArena() {
        return this.arena;
    }
}
