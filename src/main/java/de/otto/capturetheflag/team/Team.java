package de.otto.capturetheflag.team;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.utils.Starterkit;
import de.otto.capturetheflag.utils.TeamColor;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Team {

  private final CaptureTheFlag plugin;
  private final TeamColor color;
  private final Location teamSelection;
  private final Location teamSpawn;
  private final List<Player> players;
  private int score;

  public Team(CaptureTheFlag plugin, TeamColor color, Location teamSelection, Location teamSpawn) {
    this.plugin = plugin;
    this.color = color;
    this.teamSelection = teamSelection;
    this.teamSpawn = teamSpawn;
    this.players = new ArrayList<>();
  }

  public void teleportAllPlayersToTeamSpawn() {
    getPlayers().forEach(player -> player.teleport(getTeamSpawn()));
  }

  public void teleportPlayerToTeamSpawn(Player player) {
    player.teleport(getTeamSpawn());
  }

  public void addPlayer(Player player) {
    getPlayers().add(player);
  }

  public void removePlayer(Player player) {
    getPlayers().remove(player);
  }

  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
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

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void addScore() {
    this.score += 1;
    plugin.getGame().checkScore(this);
  }
  public void equipAllPlayers() {
    players.forEach(player -> Starterkit.setItems(player));
  }
}
