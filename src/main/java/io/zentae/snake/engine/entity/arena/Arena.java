package io.zentae.snake.engine.entity.arena;

import io.zentae.snake.engine.entity.ArenaEntity;
import io.zentae.snake.engine.entity.Location;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Arena {

    int getWidth();
    int getLength();

    /**
     * Register an {@link ArenaEntity} inside the {@link Arena}.
     * @param object the {@link ArenaEntity} to register.
     */
    void register(@Nonnull ArenaEntity object);

    /**
     * Unregister an {@link ArenaEntity} from the {@link Arena}.
     * @param object the {@link ArenaEntity} to unregister.
     */
    void unregister(@Nonnull ArenaEntity object);

    /**
     * Retrieve an {@link ArenaEntity} by its {@link Location}.
     * @param location the {@link Location} to retrieve the {@link ArenaEntity} from.
     * @return an {@link ArenaEntity} by its {@link Location}.
     */
    @Nullable
    ArenaEntity getObject(@Nonnull Location location);

    /**
     * @return all the occupied {@link Location}.
     */
    Collection<Location> getOccupiedSegments();

    /**
     * @return all the {@link ArenaEntity} within the {@link Arena}.
     */
    @Nonnull
    Collection<ArenaEntity> getObjects();

    /**
     * Returns all the {@link ArenaEntity} corresponding to the given class.
     * @param tClass the class to match
     * @return all the {@link ArenaEntity} corresponding to the given class.
     */
    @Nonnull
    default <T extends ArenaEntity> List<T> getObjects(@Nonnull Class<T> tClass) {
        return getObjects().stream()
                .filter(obj -> obj.getType().equals(tClass))
                .map(tClass::cast)
                .toList();
    }
}
