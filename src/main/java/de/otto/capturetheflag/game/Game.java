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
    });
    Collection<Player> ingamePlayers = getPlugin().getTeamFactory().getTeams().stream()
        .map(Team::getPlayers).flatMap(Collection::stream).collect(Collectors.toList());

    List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
    players.removeAll(ingamePlayers);
    players.forEach(player -> player.kick(
        MiniMessage.get().parse("<rainbow>Das Spiel startet jetzt</rainbow>")));
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
