package io.zentae.snake.engine.algorithm;

import io.zentae.snake.engine.movement.Movement;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

public class Path {

    // all the path within this path.
    private final List<Path> paths = new ArrayList<>();
    // the default path's weight.
    private double weight = Double.MIN_VALUE;
    // the movement leading to this path.
    private final Movement movement;

    public Path(@Nonnull Movement movement) {
        this.movement = movement;
    }

    public Movement getMovement() {
        return this.movement;
    }

    public List<Path> getPaths() {
        return this.paths;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getAllWeight() {
        double i = weight;
        for(Path path : paths)
            i += path.getWeight();
        return i;
    }
}
