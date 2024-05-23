package io.zentae.snake.engine.listener;

import io.zentae.snake.engine.entity.Location;
import io.zentae.snake.engine.entity.arena.Arena;
import io.zentae.snake.engine.entity.fruit.DefaultFruit;
import io.zentae.snake.engine.entity.fruit.Fruit;
import io.zentae.snake.engine.event.EventBus;
import io.zentae.snake.engine.event.snake.SnakeMoveEvent;

import java.util.concurrent.ThreadLocalRandom;

public class SnakeMoveListener extends Listener<SnakeMoveEvent> {

    // the randomizer.
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public SnakeMoveListener() {
        super(SnakeMoveEvent.class);
    }

    @Override
    protected void onListen(SnakeMoveEvent event) {
        // retrieve the arena.
        Arena arena = event.getArena();
        // check if there's already a fruit inside the arena.
        if(!arena.getObjects(Fruit.class).isEmpty())
            return;
        // check if we must spawn a fruit.
        if(RANDOM.nextBoolean()) {
            // loop until we found a "safe" place.
            while (true) {
                // create a new random location.
                Location random = new Location(RANDOM.nextInt(1, arena.getLength() - 1),
                        RANDOM.nextInt(1, arena.getWidth() - 1));
                // check if the location is used.
                if(arena.getOccupiedSegments().contains(random))
                    continue;
                // spawn fruit at random location.
                arena.register(new DefaultFruit(random));
                // break the loop
                break;
            }
        }
    }
}
