package de.otto.capturetheflag.listener;

import de.otto.capturetheflag.CaptureTheFlag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

public class GeneralListener extends AbstractGameListener {

  public GeneralListener(CaptureTheFlag plugin) {
    super(plugin);
  }

  @EventHandler(priority = EventPriority.LOW)
  public void onBlockBreak(BlockBreakEvent e) {
    e.setCancelled(true);
  }

}
