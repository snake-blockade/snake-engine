package io.zentae.snake.engine.event;

import io.zentae.snake.engine.controller.game.GameController;

public abstract class GameEvent implements Event {

    // the actual game.
    private final GameController game;

    public GameEvent(GameController game) {
        this.game = game;
    }

    public GameController getGame() {
        return this.game;
    }
}
