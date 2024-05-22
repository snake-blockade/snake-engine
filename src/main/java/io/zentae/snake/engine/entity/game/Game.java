package io.zentae.snake.engine.entity.game;

import io.zentae.snake.engine.entity.arena.Arena;
import io.zentae.snake.engine.entity.player.Player;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface Game {

    /**
     * @return every N laps it should make the snakes grow.
     */
    int getLapGrow();

    /**
     * Sets the current {@link Game}'s lap.
     * @param lap the lap to set.
     */
    void setLap(int lap);

    /**
     * @return the current {@link Game}'s lap.
     */
    int getLap();
    /**
     * @return the {@link Game}'s {@link GameState}.
     */
    @Nonnull
    GameState getGameState();

    /**
     * Update the {@link Game}'s {@link GameState}.
     * @param gameState the {@link GameState} to set.
     */
    void setGameState(GameState gameState);
    /**
     * @return the {@link Arena} associated to this {@link Game}.
     */
    @Nonnull
    Arena getArena();
    /**
     * @return the {@link Player Players} in this {@link Game}.
     */
    @Nonnull
    List<Player> getPlayers();
}
