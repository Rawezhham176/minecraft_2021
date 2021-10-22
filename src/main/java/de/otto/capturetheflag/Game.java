package de.otto.capturetheflag;

public class Game {
    public static boolean active = false;

    public static boolean isActive() {
        return active;
    }

    public static void setActive(boolean active) {
        Game.active = active;
    }
}
