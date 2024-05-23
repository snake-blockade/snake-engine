package io.zentae.snake.engine.movement;

public enum Movement {

    // This enum avoids illegal movements such as diagonals.
    UP(0, -1), DOWN(0, 1),
    LEFT(-1, 0), RIGHT(1, 0),
    NONE(0, 0);

    private final int x;
    private final int y;

    Movement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
