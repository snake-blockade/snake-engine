package io.zentae.snake.engine.movement;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public enum Movement {

    // This enum avoids illegal movements such as diagonals.
    UP("U", 0, -1), DOWN("D", 0, 1),
    LEFT("L", -1, 0), RIGHT("R", 1, 0),
    NONE("NONE", 0, 0);

    private final String channelInitial;
    private final int x;
    private final int y;

    Movement(String channelInitial, int x, int y) {
        this.channelInitial = channelInitial;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getChannelInitial() {
        return this.channelInitial;
    }

    /**
     * Gets a movement by its initial, returns null if no movement matches.
     * @param initial the initial of the movement.
     * @return the matching {@link Movement} of the given initial.
     */
    @Nullable
    public static Movement byInitial(@Nonnull String initial) {
        for(Movement movement : values()) {
            if(movement.channelInitial.equals(initial))
                return movement;
        }
        return null;
    }

    /**
     * Returns a {@link Movement} by its coordinates.
     * @param x the {@link Movement}'s x coordinate.
     * @param y the {@link Movement}'s y coordinate.
     * @return a {@link Movement} by its coordinates.
     */
    @Nullable
    public static Movement byOrientation(int x, int y) {
        for(Movement movement : values()) {
            if(movement.getX() == x && movement.getY() == y)
                return movement;
        }
        return null;
    }
}
