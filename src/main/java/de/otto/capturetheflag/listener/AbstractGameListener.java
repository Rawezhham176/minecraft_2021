package de.otto.capturetheflag.listener;

import de.otto.capturetheflag.CaptureTheFlag;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class AbstractGameListener implements Listener {

  private final CaptureTheFlag plugin;

  public AbstractGameListener(CaptureTheFlag plugin) {
    this.plugin = plugin;
  }

  public void registerListener(boolean register) {
    if (register) {
      getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    } else {
      HandlerList.unregisterAll(this);
    }
  }

  public CaptureTheFlag getPlugin() {
    return plugin;
  }


}
