package de.otto.capturetheflag.game;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.team.Team;

public class Game {

  private CaptureTheFlag plugin;

  public Game(CaptureTheFlag plugin) {
    this.plugin = plugin;
  }

  private boolean active = false;

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void start() {
    setActive(true);
    getPlugin().getTeamFactory().getTeams().forEach(Team::teleportAllPlayersToTeamSpawn);
  }

  public CaptureTheFlag getPlugin() {
    return plugin;
  }
}
