package de.otto.capturetheflag.game;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.team.Team;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Game {

  private final CaptureTheFlag plugin;
  private boolean active = false;

  public Game(CaptureTheFlag plugin) {
    this.plugin = plugin;
  }

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
      team.startCheckFlag();
    });
    Collection<Player> ingamePlayers = getPlugin().getTeamFactory().getTeams().stream()
        .map(Team::getPlayers).flatMap(Collection::stream).collect(Collectors.toList());

    List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
    players.removeAll(ingamePlayers);
    players.forEach(player -> player.kick(
        MiniMessage.get().parse("<rainbow>Das Spiel startet jetzt</rainbow>")));
  }

  public void winner(Team winner) {
    setActive(false);
    getPlugin().getPhaseFactory().setPhase(PhaseName.LOBBY, false);
    getPlugin().getTeamFactory().getTeams().forEach(Team::shutdown);
    Bukkit.getServer().sendMessage(MiniMessage.get().parse(
        "<yellow> Team " + winner.getColor().getChatColor() + winner.getColor().name()
            + "<yellow> hat das Spiel gewonnen."));
  }

  public void checkScore(Team scoredTeam) {

    StringBuilder stringBuilder = new StringBuilder("<yellow>Neuer Punktestand: ");

    int teamsCount = getPlugin().getTeamFactory().getTeams().size();
    for (int i = 0; i < teamsCount; i++) {
      Team team = getPlugin().getTeamFactory().getTeams().get(i);
      stringBuilder.append(team.getColor().getChatColor());
      stringBuilder.append(team.getColor().name());
      stringBuilder.append("<yellow>: <gold>");
      stringBuilder.append(team.getScore());
      if (i != teamsCount - 1) {
        stringBuilder.append("<yellow> - ");
      }
    }

    Bukkit.getServer().sendMessage(MiniMessage.get().parse(stringBuilder.toString()));
    if (scoredTeam.getScore() == 3) {
      winner(scoredTeam);
    }
  }

  public CaptureTheFlag getPlugin() {
    return plugin;
  }
}
