package io.zentae.snake.engine.factory.handler;

import io.zentae.snake.engine.handler.MovementHandler;
import io.zentae.snake.engine.handler.GameType;
import jakarta.annotation.Nullable;

public interface HandlerFactory {

    /**
     * Creates a specific game handler from a game type.
     * @param gameType the associated game type.
     * @return a brand-new, unique instance of the <code>{@link MovementHandler}</code>.
     */
    @Nullable
    MovementHandler create(GameType gameType);

}
