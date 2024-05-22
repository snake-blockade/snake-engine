package io.zentae.snake.engine.event;

import io.zentae.snake.engine.controller.game.GameController;
import io.zentae.snake.engine.entity.ArenaEntity;

public class CollisionEvent extends GameEvent {

    // the object that has done the action.
    private final ArenaEntity actor;
    // the object that is affected by the actor's action.
    private final ArenaEntity victim;

    public CollisionEvent(GameController game, ArenaEntity actor, ArenaEntity victim) {
        super(game);
        this.actor = actor;
        this.victim = victim;
    }

    public ArenaEntity getActor() {
        return this.actor;
    }

    public ArenaEntity getVictim() {
        return this.victim;
    }
}
