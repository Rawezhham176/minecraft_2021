package de.otto.capturetheflag.listener;

import de.otto.capturetheflag.CaptureTheFlag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class GeneralListener extends AbstractGameListener {

  public GeneralListener(CaptureTheFlag plugin) {
    super(plugin);
  }

  @EventHandler(priority = EventPriority.LOW)
  public void onBlockBreak(BlockBreakEvent e) {
    if (!(getPlugin().isDebugMode() && e.getPlayer().isOp())) {
      e.setCancelled(true);
    }
  }

  @EventHandler(priority = EventPriority.LOW)
  public void onBlockBreak(BlockPlaceEvent e) {
    if (!(getPlugin().isDebugMode() && e.getPlayer().isOp())) {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void onExp(PlayerExpChangeEvent e) {
    e.setAmount(0);
  }

}
