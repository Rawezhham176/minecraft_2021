package de.otto.capturetheflag;

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
    Location teamSelectionBlue = LocationUtils.TEAM_SELECTION_BLUE;
    Location teamSelectionRed = LocationUtils.TEAM_SELECTION_RED;
    if (LocationUtils.isPlayerInLocationRange(player, teamSelectionBlue, 3)) {
      player.sendMessage("§bDu bist jetzt im Team §1BLAU");
    } else if (LocationUtils.isPlayerInLocationRange(player, teamSelectionRed, 3)) {
      player.sendMessage("§bDu bist jetzt im Team §4ROT");
    }
  }

  public CaptureTheFlag getPlugin() {
    return plugin;
  }
}
