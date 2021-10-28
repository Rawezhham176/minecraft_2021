package de.otto.capturetheflag.team;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.utils.TeamColor;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeamFactory {

  private final CaptureTheFlag plugin;
  private final List<Team> teams = new ArrayList<>();

  public TeamFactory(CaptureTheFlag plugin) {
    this.plugin = plugin;
  }

  public void addTeam(TeamColor color, Location teamSelection, Location teamSpawn) {
    teams.add(new Team(plugin, color, teamSelection, teamSpawn));
  }

  public Team getTeamByColor(TeamColor color) {
    return getTeams().stream().filter(team -> team.getColor() == color).findFirst().orElseThrow();
  }

  public List<Team> getTeams() {
    return teams;
  }

  public Team getTeamByPlayer(Player player) {
    return getTeams().stream().filter(team -> team.containsPlayer(player)).findFirst().orElseThrow();
  }
}
