package io.zentae.snake.engine.entity.snake;

import io.zentae.snake.engine.controller.game.GameController;
import io.zentae.snake.engine.entity.ArenaEntity;
import io.zentae.snake.engine.entity.Location;
import io.zentae.snake.engine.entity.fruit.Fruit;
import io.zentae.snake.engine.entity.obstacle.Obstacle;

import java.util.Collection;
import java.util.List;

public class DefaultSnake implements Snake {

    // the body.
    private final List<Location> body;

    public DefaultSnake(List<Location> body) {
        this.body = body;
    }

    @Override
    public List<Location> getBody() {
        return this.body;
    }

    @Override
    public Location getHead() {
        return this.body.get(0);
    }

    @Override
    public Location getTail() {
        return this.body.get(body.size() - 1);
    }

    @Override
    public Collection<Location> getSegments() {
        return this.body;
    }

    @Override
    public void onCollide(GameController game, ArenaEntity other) {
        // check if the entity hit is a snake.
        if(other instanceof Snake snake) {
            // check if it's the same snake.
            if(snake.equals(this) && this.getBody().size() > 2) {
                System.out.println("IL S'EST MANGE");
                // end the game.
                game.end();
            } else if(!snake.equals(this)){
                System.out.println("IL EST DANS UN AUTRE !");
                game.end();
            }
        } else if (other instanceof Fruit) {
            System.out.println("IL A GRAILLE UN FRUIT !");
            this.getBody().remove(this.getTail());
            // despawn the fruit.
            game.getGame().getArena().unregister(other);
        } else if(other instanceof Obstacle) {
            System.out.println("IL S'EST MANGE UN OBSTACLE");
            game.end();
        }
    }
}
