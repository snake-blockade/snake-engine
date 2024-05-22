package io.zentae.snake.engine.event;

import io.zentae.snake.engine.controller.game.GameController;

public class GameEndEvent extends GameEvent {

    public GameEndEvent(GameController game) {
        super(game);
    }
}
