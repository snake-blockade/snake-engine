package io.zentae.snake.engine.algorithm;

import io.zentae.snake.engine.controller.game.GameController;

public abstract class Algorithm<T, U> {

    // the number of recursive layer.
    private final int layer;

    public Algorithm(int layer) {
        this.layer = layer;
    }

    /**
     * Do the initialization here.
     * @param game the game where the AI is playing in.
     * @return a result.
     */
    public abstract T process(GameController game);

    /**
     * The layered algorithm. This is a recursive function.
     * @param objects the arguments.
     * @return a value.
     */
    protected abstract U next(Object... objects);

    /**
     * @return the number of layer that the algorithm should have.
     */
    protected int getLayer() {
        return this.layer;
    }
}
