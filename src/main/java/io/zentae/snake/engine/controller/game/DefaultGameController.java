package io.zentae.snake.engine.controller.game;

import io.zentae.snake.engine.controller.snake.SnakeController;
import io.zentae.snake.engine.entity.ArenaEntity;
import io.zentae.snake.engine.entity.Location;
import io.zentae.snake.engine.entity.arena.Arena;
import io.zentae.snake.engine.entity.game.Game;
import io.zentae.snake.engine.entity.game.GameState;
import io.zentae.snake.engine.entity.player.Player;
import io.zentae.snake.engine.entity.snake.Snake;
import io.zentae.snake.engine.event.CollisionEvent;
import io.zentae.snake.engine.event.EventBus;
import io.zentae.snake.engine.event.GameEndEvent;
import io.zentae.snake.engine.event.snake.SnakeGrowEvent;
import io.zentae.snake.engine.event.snake.SnakeMoveEvent;
import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public class DefaultGameController implements GameController {

    // get death type.
    private DeathType deathType;
    // the game.
    private final Game game;
    // the current player index.
    private int playerIndex = 0;

    public DefaultGameController(Game game) {
        this.game = game;
    }

    @Override
    public void next(@Nonnull Movement movement) {
        // get snake controller.
        SnakeController snakeController = getCurrentPlayer().getController();
        // predict the movement.
        Location prediction = snakeController.predict(movement);
        // retrieve the arena.
        Arena arena = this.getGame().getArena();
        // retrieve the snake.
        Snake snake = snakeController.getSnake();
        // try to find a collision point.
        ArenaEntity victim = arena.getObject(prediction);
        // check if there will be a collision.
        if(victim != null) {
            // dispatch a collision event.
            EventBus.dispatch(new CollisionEvent(this, snake, victim));
        }
        // check if the snake is going inside himself.
        // check if the snake is out of bound.
        if((prediction.getX() >= arena.getLength()
                || prediction.getY() >= arena.getWidth())
                || prediction.getX() < 0
                || prediction.getY() < 0) {
            // set death type.
            this.setDeathType(DeathType.ARENA_EXIT);
            // update the game-state.
            this.end();
            return;
        }
        // make the snake move.
        snakeController.move(movement);
        // check if we need to make the player grow.
        if(this.game.getLap() % this.game.getLapGrow() == 0
                && this.game.getLap() != 0) {
            // dispatch event.
            EventBus.dispatch(new SnakeGrowEvent(this, snakeController));
        }
        // increase player index.
        this.playerIndex++;
        // check if all the players has played.
        if(playerIndex % getGame().getPlayers().size() == 0)
            // increment the lap count.
            this.game.setLap(game.getLap() + 1);
        // dispatch a move.
        EventBus.dispatch(new SnakeMoveEvent(this, snake));
    }

    @Override
    public void end() {
        this.game.setGameState(GameState.END);
        // dispatch event.
        EventBus.dispatch(new GameEndEvent(this));
    }

    @Override
    public Game getGame() {
        return this.game;
    }

    private Player getCurrentPlayer() {
        // avoid out of bound exception.
        return game.getPlayers().get(playerIndex % game.getPlayers().size());
    }

    @Nullable
    @Override
    public void setDeathType(DeathType deathType) {
        this.deathType = deathType;
    }

    @Override
    public DeathType getDeathType() {
        return this.deathType;
    }
}
