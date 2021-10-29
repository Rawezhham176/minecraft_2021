package de.otto.capturetheflag.flagcarrier;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.team.Team;
import de.otto.capturetheflag.team.TeamPlayer;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlagCarrierManager {

  private final CaptureTheFlag plugin;
  private final HashMap<Team, TeamPlayer> carriedFlags = new HashMap<>();

  public FlagCarrierManager(CaptureTheFlag plugin) {
    this.plugin = plugin;
  }

  public void takeFlag(Team carriedFlag, TeamPlayer player) {
    carriedFlags.put(carriedFlag, player);
  }

  public void removeFlag(Team team) {
    carriedFlags.remove(team);
  }

  public Optional<Team> getTeamFlagCarriedByTeamPlayer(TeamPlayer teamPlayer) {
    return carriedFlags.entrySet().stream()
        .filter(teamPlayerTeamEntry -> teamPlayerTeamEntry.getValue() == teamPlayer).findAny().map(
            Entry::getKey);
  }

  public boolean isPlayerNotCarryingFlag(TeamPlayer thisPlayer) {
    return carriedFlags.values().stream().noneMatch(player -> player == thisPlayer);
  }

  public Optional<TeamPlayer> getPlayerByTeamFlag(Team team) {
    return Optional.ofNullable(carriedFlags.getOrDefault(team, null));
  }

  public List<Entry<Team, TeamPlayer>> getTeamMembersWithEnemyTeamFlag(Team team) {
    return carriedFlags.entrySet().stream()
        .filter(teamTeamPlayerEntry -> teamTeamPlayerEntry.getValue().getPlayerTeam() == team)
        .collect(
            Collectors.toList());
  }
}
