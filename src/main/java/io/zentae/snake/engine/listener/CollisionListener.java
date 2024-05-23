package io.zentae.snake.engine.listener;

import io.zentae.snake.engine.entity.Collidable;
import io.zentae.snake.engine.event.CollisionEvent;
import io.zentae.snake.engine.event.EventBus;

public class CollisionListener extends Listener<CollisionEvent> {

    public CollisionListener() {
        super(CollisionEvent.class);
    }

    @Override
    protected void onListen(CollisionEvent event) {
        if(!(event.getActor() instanceof Collidable collidable))
            return;
        collidable.onCollide(event.getGame(), event.getVictim());
    }
}
