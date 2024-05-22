package io.zentae.snake.engine.factory.snake;

import io.zentae.contentlibrary.random.DefaultRandomizer;
import io.zentae.contentlibrary.random.Randomizer;
import io.zentae.snake.engine.entity.Location;
import io.zentae.snake.engine.entity.snake.DefaultSnake;
import io.zentae.snake.engine.entity.snake.Snake;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

public class DefaultSnakeFactory implements SnakeFactory {

    // the randomizer.
    private static final Randomizer<Integer> X_RANDOMIZER;
    private static final Randomizer<Integer> Y_RANDOMIZER;

    static {
        X_RANDOMIZER = new DefaultRandomizer.Builder<Integer>()
                .ratio(3)
                .entry(1, -1)
                .entry(1, 0)
                .entry(1, 1)
                .build();
        Y_RANDOMIZER = new DefaultRandomizer.Builder<Integer>()
                .ratio(2)
                .entry(1, -1)
                .entry(1, 1)
                .build();
    }

    @Override
    @Nonnull
    public Snake create(@Nonnull Location location) {
        // prepare our location buffer.
        List<Location> locationBuffer = new ArrayList<>();
        // add the head.
        locationBuffer.add(location);
        // chooses a random number between -1, 0, 1
        int x = X_RANDOMIZER.random();
        // we check if we moved on the x-axis if so we don't move on the y-axis.
        // else we move on the y-axis to avoid placing two body part at the same location.
        int y = x == 0
                ? Y_RANDOMIZER.random()
                : 0;
        // add the tail.
        locationBuffer.add(new Location(location.getX() + x, location.getY() + y));
        // return the snake.
        return new DefaultSnake(locationBuffer);
    }

    @Override
    @Nonnull
    public Snake create(int size) {
        // not implemented yet.
        return null;
    }
}
