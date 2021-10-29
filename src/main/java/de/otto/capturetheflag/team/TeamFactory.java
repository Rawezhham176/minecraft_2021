package de.otto.capturetheflag.team;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.utils.TeamColor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TeamFactory {

  private final CaptureTheFlag plugin;
  private final List<Team> teams = new ArrayList<>();
  private final List<TeamPlayer> teamPlayers = new ArrayList<>();

  public TeamFactory(CaptureTheFlag plugin) {
    this.plugin = plugin;
  }

  public TeamPlayer getTeamPlayerByPlayer(Player player) {
    return teamPlayers.stream().filter(teamPlayer -> teamPlayer.getPlayer() == player).findAny()
        .orElseThrow();
  }

//  public Team getTeamByColor(TeamColor color) {
//    return getTeams().stream().filter(team -> team.getColor() == color).findFirst().orElseThrow();
//  }

  public void addTeam(TeamColor color, Location teamSelection, Location teamSpawn,
      Location teamFlag, Material teamBlock) {
    teams.add(new Team(plugin, color, teamSelection, teamSpawn, teamFlag, teamBlock));
  }

  public boolean addPlayerToTeam(Player player, Team team) {
    // Player nicht da -> hinzufügen
    // Player da und in Team x -> nichts machen
    // Player da und nicht in Team x -> in x hinzufügen
    AtomicBoolean addPlayer = new AtomicBoolean(false);
    teamPlayers.stream().filter(teamPlayer -> teamPlayer.getPlayer() == player).findAny()
        .ifPresentOrElse(teamPlayer -> {
              if (teamPlayer.getPlayerTeam() != team) {
                teamPlayer.setPlayerTeam(team);
                addPlayer.set(true);
              }
            },
            () -> {
              teamPlayers.add(new TeamPlayer(player, team));
              addPlayer.set(true);
            });
    return addPlayer.get();

  }

  public List<Team> getTeams() {
    return teams;
  }

  public List<TeamPlayer> getTeamPlayers() {
    return teamPlayers;
  }

  public Team getTeamByPlayer(Player player) {
    return teamPlayers.stream().filter(teamPlayer -> teamPlayer.getPlayer() == player)
        .findAny().map(TeamPlayer::getPlayerTeam).orElseThrow();
  }

  public List<Team> otherTeams(Team thisTeam) {
    return teams.stream().filter(team -> team != thisTeam).collect(Collectors.toList());
  }
}
