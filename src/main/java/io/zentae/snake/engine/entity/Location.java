package io.zentae.snake.engine.entity;

import io.zentae.snake.engine.movement.Movement;

public class Location {

    // the x coordinate.
    private int x;
    // the y coordinate.
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds an x and y offset to the current coordinates.
     * @param x the x offset.
     * @param y the y offset.
     */
    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Adds an x and y offset to the current coordinates based on a {@link Movement}.
     * @param movement the {@link Movement} to add.
     */
    public void add(Movement movement) {
        this.add(movement.getX(), movement.getY());
    }

    /**
     * Sets the coordinates to a given x and y coordinates.
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Multiply the coordinates by a multiplier.
     * @param multiplier the multiplier.
     */
    public void multiply(int multiplier) {
        this.x *= multiplier;
        this.y *= multiplier;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // we don't allow cloning for simplicity.
        // avoids weird behaviors and unnecessary headaches.
        throw new CloneNotSupportedException("Cannot clone location due to unsafe memory leaks.");
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof Location))
            return false;
        return obj.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + x;
        hash = 31 * hash + y;
        return hash;
    }
}
