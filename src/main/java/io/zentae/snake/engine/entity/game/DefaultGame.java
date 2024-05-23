package io.zentae.snake.engine.entity.game;

import io.zentae.snake.engine.entity.arena.Arena;
import io.zentae.snake.engine.entity.player.Player;
import io.zentae.snake.engine.handler.GameHandler;
import jakarta.annotation.Nonnull;

import java.util.Collections;
import java.util.List;

public class DefaultGame implements Game {

    // the current game mode.
    private final GameHandler gameMode;
    // the actual lap.
    private int lap = 0;
    // the number of laps between each the snakes should grow.
    private final int lapGrow;
    // the arena.
    private final Arena arena;
    // the game-state.
    private GameState gameState;
    // the players.
    private final List<Player> players;

    public DefaultGame(@Nonnull GameHandler gameMode, @Nonnull Arena arena, @Nonnull List<Player> players,
                       int lapGrow) {
        this.gameMode = gameMode;
        this.arena = arena;
        this.lapGrow = lapGrow;
        this.players = players;
        this.gameState = GameState.PLAYING;
    }

    @Override
    @Nonnull
    public GameHandler getGameHandler() {
        return this.gameMode;
    }

    @Override
    public int getLapGrow() {
        return this.lapGrow;
    }

    @Override
    public void setLap(int lap) {
        this.lap = lap;
    }

    @Override
    public int getLap() {
        return this.lap;
    }

    @Nonnull
    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public void setGameState(@Nonnull GameState gameState) {
        this.gameState = gameState;
    }

    @Nonnull
    @Override
    public Arena getArena() {
        return this.arena;
    }

    @Nonnull
    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
