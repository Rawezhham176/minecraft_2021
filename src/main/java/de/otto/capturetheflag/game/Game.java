package de.otto.capturetheflag.game;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.team.Team;

public class Game {

  private final CaptureTheFlag plugin;

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
    getPlugin().getPhaseFactory().setPhase(PhaseName.LOBBY, false);
    getPlugin().getPhaseFactory().setPhase(PhaseName.INGAME, true);
    getPlugin().getTeamFactory().getTeams().forEach(team -> {
      team.equipAllPlayers();
      team.teleportAllPlayersToTeamSpawn();
    });
  }

  public void checkScore(Team team) {
    //noinspection StatementWithEmptyBody
    if (team.getScore() == 3) {

    }
  }

  public CaptureTheFlag getPlugin() {
    return plugin;
  }
}
