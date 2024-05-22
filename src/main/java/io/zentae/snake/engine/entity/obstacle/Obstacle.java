package io.zentae.snake.engine.entity.obstacle;

import io.zentae.snake.engine.entity.ArenaEntity;

public interface Obstacle extends ArenaEntity {

    @Override
    default Class<?> getType() {
        return Obstacle.class;
    }
}
