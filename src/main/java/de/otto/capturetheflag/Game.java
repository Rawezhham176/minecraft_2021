package de.otto.capturetheflag;

public class Game {
    public boolean active = false;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void start() {
        setActive(true);

    }
}
