package io.zentae.snake.engine.factory.arena;

import io.zentae.snake.engine.entity.Location;
import io.zentae.snake.engine.entity.arena.Arena;
import io.zentae.snake.engine.entity.arena.DefaultArena;
import io.zentae.snake.engine.entity.obstacle.DefaultObstacle;
import io.zentae.snake.engine.entity.snake.Snake;
import jakarta.annotation.Nonnull;

import java.util.List;

public class LinearArenaFactory implements ArenaFactory {

    @Override
    public Arena create(@Nonnull List<Snake> snakes, int length, int width) {
        // create the arena.
        Arena arena = new DefaultArena(length, width);
        // loop through each snake.
        for(Snake snake : snakes)
            arena.register(snake);
        // register rocks.
        arena.register(new DefaultObstacle(new Location(4, 4)));
        arena.register(new DefaultObstacle(new Location(5, 4)));
        arena.register(new DefaultObstacle(new Location(6, 4)));
        arena.register(new DefaultObstacle(new Location(7, 4)));
        arena.register(new DefaultObstacle(new Location(4, 17)));
        arena.register(new DefaultObstacle(new Location(5, 17)));
        arena.register(new DefaultObstacle(new Location(6, 17)));
        arena.register(new DefaultObstacle(new Location(7, 17)));
        // return our arena.
        return arena;
    }
}
