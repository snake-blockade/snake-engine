package io.zentae.snake.engine.entity.arena;

import io.zentae.snake.engine.entity.ArenaEntity;
import io.zentae.snake.engine.entity.Location;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultArena implements Arena {

    // the entities within the arena.
    private final List<ArenaEntity> entities = new ArrayList<>();

    private final int width;
    private final int length;

    public DefaultArena(int length, int width) {
        this.width = width;
        this.length = length;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public void register(@Nonnull ArenaEntity object) {
        this.entities.add(object);
    }

    @Override
    public void unregister(@Nonnull ArenaEntity object) {
        this.entities.remove(object);
    }

    @Nullable
    @Override
    public ArenaEntity getObject(@Nonnull Location location) {
        // loop through each entity.
        for(ArenaEntity entity : this.entities) {
            // loop through each segment.
            for(Location segment : entity.getSegments()) {
                // check if the location matches.
                if(location.equals(segment))
                    return entity;
            }
        }
        return null;
    }

    @Override
    public Collection<Location> getOccupiedSegments() {
        // our segment buffer.
        Collection<Location> buffer = new ArrayList<>();
        entities.forEach(entity -> buffer.addAll(entity.getSegments()));
        return buffer;
    }

    @Nonnull
    @Override
    public Collection<ArenaEntity> getObjects() {
        return this.entities;
    }
}
