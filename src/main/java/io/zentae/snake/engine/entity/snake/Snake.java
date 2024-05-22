package io.zentae.snake.engine.entity.snake;

import io.zentae.snake.engine.entity.ArenaEntity;
import io.zentae.snake.engine.entity.Collidable;
import io.zentae.snake.engine.entity.Location;

import java.util.List;

public interface Snake extends ArenaEntity, Collidable {

    List<Location> getBody();
    Location getHead();
    Location getTail();

    @Override
    default Class<?> getType() {
        return Snake.class;
    }
}
