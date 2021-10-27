package de.otto.capturetheflag.listener;

import de.otto.capturetheflag.CaptureTheFlag;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class InGameListener extends AbstractGameListener {

  public InGameListener(CaptureTheFlag plugin) {
    super(plugin);
  }

  @EventHandler
  public void onLogin(PlayerLoginEvent e) {
    if (getPlugin().getGame().isActive()) {
      e.disallow(Result.KICK_WHITELIST,
          MiniMessage.get().parse("<rainbow>Das Spiel läuft bereits</rainbow>"));
    }
  }
}
