package de.otto.capturetheflag;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
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
    Location location = e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation();
    if (location.equals(LocationUtils.TEAMSELECTION_BLUE)) {
      player.sendMessage("§bDu bist jetzt im Team §1BLAU");
    } else if (location.equals(LocationUtils.TEAMSELECTION_RED)) {
      player.sendMessage("§bDu bist jetzt im Team §4ROT");
    }
  }

  public CaptureTheFlag getPlugin() {
    return plugin;
  }
}
