package io.zentae.snake.engine.controller.game;

public enum DeathType {

    ARENA_EXIT("Vous êtes sorti de l'arène !"),
    EAT_SELF("Vous vous êtes mordu la queue !"),
    HIT_OBSTACLE("Vous avez percuté un obstacle !"),
    HIT_OTHER_SNAKE("Vous avez percuté l'autre serpent !"),
    NONE("Vous n'êtes pas mort ! Que faites-vous ici ?");

    private final String message;
    DeathType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
