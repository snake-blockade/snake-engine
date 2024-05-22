package io.zentae.snake.engine.event.snake;

import io.zentae.snake.engine.controller.game.GameController;
import io.zentae.snake.engine.entity.player.Player;
import io.zentae.snake.engine.entity.snake.Snake;
import io.zentae.snake.engine.event.GameEvent;
import jakarta.annotation.Nonnull;

public class SnakeFruitEatEvent extends GameEvent {


    private final Snake snake;
    private final Player player;

    public SnakeFruitEatEvent(GameController game, Player player) {
        super(game);
        this.player = player;
        this.snake = player.getController().getSnake();
    }

    @Nonnull
    public Player getPlayer() {
        return this.player;
    }

    @Nonnull
    public Snake getSnake() {
        return this.snake;
    }
}
