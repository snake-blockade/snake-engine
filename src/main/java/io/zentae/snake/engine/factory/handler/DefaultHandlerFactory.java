package io.zentae.snake.engine.factory.handler;

import io.zentae.snake.engine.handler.*;
import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DefaultHandlerFactory implements HandlerFactory {

    private final Map<GameType, MovementHandler> dataMap = new HashMap<>();
    public DefaultHandlerFactory() {
        this.register(GameType.LOCAL_AI, new AIMovementHandler());
        this.register(GameType.LAN, new LANHandler());
        this.register(GameType.LOCAL_1V1, new OneVersusOneMovementHandler());
    }

    /**
     * Registers the given data into the data map, which will further allow
     * @param gameType the game type.
     * @param movementHandler the game handler.
     */
    public void register(GameType gameType, MovementHandler movementHandler) {
        this.dataMap.put(gameType, movementHandler);
    }

    @Override
    @Nullable
    public MovementHandler create(GameType gameType) {
        // if the data map does not contain the game type.
        if(!this.dataMap.containsKey(gameType))
            return null;
        // return the saved game handler.
        return this.dataMap.get(gameType);
    }
}
