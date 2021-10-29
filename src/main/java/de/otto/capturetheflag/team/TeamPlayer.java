package de.otto.capturetheflag.team;

import org.bukkit.entity.Player;

public class TeamPlayer {

  private final Player player;
  private Team playerTeam;

  public TeamPlayer(Player player, Team playerTeam) {
    this.player = player;
    this.playerTeam = playerTeam;
  }

  public Player getPlayer() {
    return player;
  }

  public Team getPlayerTeam() {
    return playerTeam;
  }

  public void setPlayerTeam(Team playerTeam) {
    this.playerTeam = playerTeam;
  }
}
