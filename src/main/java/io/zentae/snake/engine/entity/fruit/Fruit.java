package io.zentae.snake.engine.entity.fruit;

import io.zentae.snake.engine.entity.ArenaEntity;

public interface Fruit extends ArenaEntity {

    @Override
    default Class<?> getType() {
        return Fruit.class;
    }
}
