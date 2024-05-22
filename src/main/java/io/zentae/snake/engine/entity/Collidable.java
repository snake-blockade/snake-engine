package io.zentae.snake.engine.entity;

import io.zentae.snake.engine.controller.game.GameController;

public interface Collidable {

    void onCollide(GameController game, ArenaEntity other);
}
