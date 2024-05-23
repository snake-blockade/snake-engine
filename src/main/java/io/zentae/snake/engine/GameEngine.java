package io.zentae.snake.engine;

import io.zentae.snake.engine.controller.game.GameController;
import io.zentae.snake.engine.entity.game.Game;
import io.zentae.snake.engine.handle.GameHandler;
import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nonnull;

import java.util.Optional;

public class GameEngine {

    private static boolean WAITS_NEXT_MOVE = false;

    private static GameEngine GAME_ENGINE;
    private static GameController CONTROLLER;
    private GameEngine(GameController controller) {
        CONTROLLER = controller;
        GAME_ENGINE = this;
    }

    /**
     * Plays the input that has been given
     * @param movement
     * @param awaits
     */
    public static void play(Movement movement, boolean awaits) {
        // if the engine is not loaded.
        if(GAME_ENGINE == null)
            throw new RuntimeException("The engine has not been loaded yet !");
        // if waiting for another move.
        if(WAITS_NEXT_MOVE)
            return;
        // get game.
        Game game = CONTROLLER.getGame();
        // get game mode.
        GameHandler gameHandler = game.getGameMode();
        // get definitive movement that needs to be played.
        movement = gameHandler.play(Optional.ofNullable(movement));
        // play the move.
        CONTROLLER.next(movement);
        // check if the game handler awaits for another move. if so, wait for the move and play it.
        if(awaits && gameHandler.needsNextMove()) {
            // set the waiting for move as true.
            WAITS_NEXT_MOVE = true;
            // send the request and wait.
            CONTROLLER.next(gameHandler.await());
            // set the waiting time to false.
            WAITS_NEXT_MOVE = false;
        }
    }

    public static void start(boolean playsFirst) {
        // if the engine is not loaded.
        if(GAME_ENGINE == null)
            throw new RuntimeException("The engine has not been loaded yet !");
        // if the player plays first, we just wait for the player to play, the method play will be called.
        if(playsFirst)
            return;
        // play the move that has been decided by the algorithm.
        play(Movement.UP, false);
    }

    public static GameEngine loadEngine(@Nonnull GameController controller) {
        return GAME_ENGINE == null ? new GameEngine(controller) : GAME_ENGINE;
    }


}
