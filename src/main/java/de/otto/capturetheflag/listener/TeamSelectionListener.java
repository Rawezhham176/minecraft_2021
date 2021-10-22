package de.otto.capturetheflag.listener;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.utils.TeamColor;
import de.otto.capturetheflag.team.Team;
import de.otto.capturetheflag.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TeamSelectionListener implements Listener {

  private final CaptureTheFlag plugin;

  public TeamSelectionListener(CaptureTheFlag plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onMove(PlayerMoveEvent e) {
    Player player = e.getPlayer();
    Location teamSelectionBlue = LocationUtils.TEAM_BLUE_SELECTION;
    Location teamSelectionRed = LocationUtils.TEAM_RED_SELECTION;
    Team teamBlue = getPlugin().getTeamFactory().getTeamByColor(TeamColor.BLUE);
    Team teamRed = getPlugin().getTeamFactory().getTeamByColor(TeamColor.RED);
    if (LocationUtils.isPlayerInLocationRange(player, teamSelectionBlue, 3)) {
      if (!teamBlue.containsPlayer(player)) {
        teamBlue.addPlayer(player);
        teamRed.removePlayer(player);
        player.sendMessage("§bDu bist jetzt im Team §1BLAU");
      }
    } else if (LocationUtils.isPlayerInLocationRange(player, teamSelectionRed, 3)) {
      if (!teamRed.containsPlayer(player)) {
        teamRed.addPlayer(player);
        teamBlue.removePlayer(player);
        player.sendMessage("§bDu bist jetzt im Team §4ROT");
      }
    }
  }

  public CaptureTheFlag getPlugin() {
    return plugin;
  }
}
