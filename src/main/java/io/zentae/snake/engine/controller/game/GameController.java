package io.zentae.snake.engine.controller.game;

import io.zentae.snake.engine.entity.game.Game;
import io.zentae.snake.engine.entity.player.Player;
import io.zentae.snake.engine.movement.Movement;

public interface GameController {

    /**
     * Plays the next given {@link Movement} for the given {@link Player}.
     * @param movement the {@link Movement} to make.
     */
    void next(Movement movement);

    /**
     * Ends the game.
     */
    void end();

    /**
     * @return the {@link Game} that is being controlled.
     */
    Game getGame();
}
