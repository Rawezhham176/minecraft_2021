package de.otto.capturetheflag.team;

import de.otto.capturetheflag.utils.TeamColor;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;

public class TeamFactory {

  private final List<Team> teams = new ArrayList<>();

  public void addTeam(TeamColor color, Location teamSelection, Location teamSpawn) {
    teams.add(new Team(color, teamSelection, teamSpawn));
  }

  public Team getTeamByColor(TeamColor color) {
    return getTeams().stream().filter(team -> team.getColor() == color).findFirst().orElseThrow();
  }

  public List<Team> getTeams() {
    return teams;
  }
}
