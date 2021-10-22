package de.otto.capturetheflag.team;

import de.otto.capturetheflag.utils.TeamColor;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Team {

  private final TeamColor color;
  private final Location teamSelection;
  private final Location teamSpawn;
  private final List<Player> players;

  public Team(TeamColor color, Location teamSelection, Location teamSpawn) {
    this.color = color;
    this.teamSelection = teamSelection;
    this.teamSpawn = teamSpawn;
    this.players = new ArrayList<>();
  }

  public void teleportAllPlayersToTeamSpawn() {
    getPlayers().forEach(player -> player.teleport(getTeamSpawn()));
  }

//  public void teleportPlayerToTeamSpawn(Player player) {
//    player.teleport(getTeamSpawn());
//  }

  public void addPlayer(Player player) {
    getPlayers().add(player);
  }

  public void removePlayer(Player player) {
    getPlayers().remove(player);
  }

  public boolean containsPlayer(Player player) {
    return getPlayers().contains(player);
  }

  public TeamColor getColor() {
    return color;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public Location getTeamSelection() {
    return teamSelection;
  }

  public Location getTeamSpawn() {
    return teamSpawn;
  }

}
