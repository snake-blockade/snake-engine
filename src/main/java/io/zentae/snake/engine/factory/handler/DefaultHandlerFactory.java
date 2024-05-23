package io.zentae.snake.engine.factory.handler;

import io.zentae.snake.engine.handler.*;
import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DefaultHandlerFactory implements HandlerFactory {

    private final Map<GameType, GameHandler> dataMap = new HashMap<>();
    public DefaultHandlerFactory() {
        this.register(GameType.LOCAL_AI, new AIGameHandler());
        this.register(GameType.LAN, new LANHandler());
        this.register(GameType.LOCAL_1V1, new OneVersusOneGameHandler());
    }

    /**
     * Registers the given data into the data map, which will further allow
     * @param gameType the game type.
     * @param gameHandler the game handler.
     */
    public void register(GameType gameType, GameHandler gameHandler) {
        this.dataMap.put(gameType, gameHandler);
    }

    @Override
    @Nullable
    public GameHandler create(GameType gameType) {
        // if the data map does not contain the game type.
        if(!this.dataMap.containsKey(gameType))
            return null;
        // return the saved game handler.
        return this.dataMap.get(gameType);
    }
}
