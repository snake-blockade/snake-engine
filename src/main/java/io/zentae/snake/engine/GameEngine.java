package io.zentae.snake.engine;

import io.zentae.snake.engine.controller.game.GameController;
import io.zentae.snake.engine.entity.game.Game;
import io.zentae.snake.engine.handler.GameHandler;
import io.zentae.snake.engine.handler.GameType;
import io.zentae.snake.engine.io.ProtocolCoordinator;
import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nonnull;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class GameEngine {

    private static boolean WAITS_NEXT_MOVE = false;
    private static GameEngine GAME_ENGINE;

    private static GameType GAME_TYPE;
    private static GameController CONTROLLER;
    private GameEngine(GameType gameType, GameController controller) {
        CONTROLLER = controller;
        GAME_TYPE = GAME_TYPE;
        GAME_ENGINE = this;
    }

    /**
     * Plays the input that has been given by the player, and another move if needed.
     * @param movement the input of the player, translated into a movement.
     * @param awaits whether the engine needs to wait for a computer (AI/other PC) move.
     */
    public static void play(Movement movement, boolean awaits) {
        // if the engine is not loaded.
        if(GAME_ENGINE == null)
            throw new RuntimeException("The engine has not been loaded yet !");
        // if waiting for another move and needs to wait for this move.
        if(awaits && WAITS_NEXT_MOVE)
            return;
        // get game.
        Game game = CONTROLLER.getGame();
        // get game mode.
        GameHandler gameHandler = game.getGameHandler();
        // get definitive movement that needs to be played.
        movement = gameHandler.play(Optional.ofNullable(movement));
        // play the move.
        CONTROLLER.next(movement);
        // if it's a LAN party, then send the data to the opponent.
        if(GAME_TYPE == GameType.LAN) ProtocolCoordinator.get().send(movement);
        // check if the game handler awaits for another move. if so, wait for the move and play it.
        if(awaits && gameHandler.needsNextMove()) {
            // set the waiting for move as true.
            WAITS_NEXT_MOVE = true;
            // send the request and wait.
            gameHandler.await(opponentMovement -> {
                // play the move.
                CONTROLLER.next(opponentMovement);
                // set the waiting time to false.
                WAITS_NEXT_MOVE = false;
            });
        }
    }

    public static void start(GameType gameType, boolean playsFirst) {
        // if the engine is not loaded.
        if(GAME_ENGINE == null)
            throw new RuntimeException("The engine has not been loaded yet !");
        // if the player plays first, we just wait for the player to play, the method play will be called.
        if(playsFirst)
            return;
        // decide what will the first move be.
        CompletableFuture<Movement> future = new CompletableFuture<>();
        if(gameType == GameType.LAN)
            ProtocolCoordinator.get().getOpponentMove(future::complete);
        if(gameType == GameType.LOCAL_AI)
            CONTROLLER.getGame().getGameHandler().await(future::complete);
        // set as waiting for opponent's move.
        WAITS_NEXT_MOVE = true;
        // wait for the callback.
        future.thenAccept(movement -> {
            // play the move.
            play(movement, false);
            // set as not waiting for the move.
            WAITS_NEXT_MOVE = false;
        });
    }

    public static GameEngine loadEngine(GameType gameType, @Nonnull GameController controller) {
        System.out.println("[*] Loading the engine...");
        return GAME_ENGINE == null ? new GameEngine(gameType, controller) : GAME_ENGINE;
    }


}
