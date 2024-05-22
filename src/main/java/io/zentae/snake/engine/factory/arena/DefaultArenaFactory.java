package io.zentae.snake.engine.factory.arena;

import io.zentae.snake.engine.entity.Location;
import io.zentae.snake.engine.entity.arena.Arena;
import io.zentae.snake.engine.entity.arena.DefaultArena;
import io.zentae.snake.engine.entity.obstacle.DefaultObstacle;
import io.zentae.snake.engine.entity.snake.Snake;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultArenaFactory implements ArenaFactory {

    // the randomizer.
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Override
    public Arena create(@Nonnull List<Snake> snakes, int length, int width) {
        // create the arena.
        Arena arena = new DefaultArena(length, width);
        // loop through each snake.
        for(Snake snake : snakes)
            arena.register(snake);
        // place obstacles.
        int placed = 0;
        // loop until we placed all the obstacles.
        while (placed != RANDOM.nextInt(5)) {
            // loop until we found a "safe" place.
            while (true) {
                // create a new random location.
                Location random = new Location(RANDOM.nextInt(1, arena.getLength() - 1),
                        RANDOM.nextInt(1, arena.getWidth() - 1));
                // check if the location is used.
                if(arena.getOccupiedSegments().contains(random))
                    continue;
                // spawn fruit at random location.
                arena.register(new DefaultObstacle(random));
                // break the loop
                break;
            }
        }
        // return our arena.
        return arena;
    }
}
