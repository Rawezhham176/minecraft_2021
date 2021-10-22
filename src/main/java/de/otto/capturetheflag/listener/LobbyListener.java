package de.otto.capturetheflag.listener;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.utils.LocationUtils;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class LobbyListener implements Listener {

  private final CaptureTheFlag plugin;

  public LobbyListener(CaptureTheFlag plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onHunger(FoodLevelChangeEvent e) {
    e.setCancelled(true);
  }

  @EventHandler
  public void onDamage(EntityDamageEvent e) {
    if (e.getEntity() instanceof Player) {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void onJoinListener(PlayerJoinEvent e) {
    e.getPlayer().teleport(LocationUtils.LOBBY_SPAWN);
  }

  @EventHandler
  public void onLogin(PlayerLoginEvent e) {
    if (getPlugin().getGame().isActive()) {
      e.disallow(Result.KICK_WHITELIST,
          MiniMessage.get().parse("<rainbow>Das Spiel läuft bereits</rainbow>"));
    }
  }

  public CaptureTheFlag getPlugin() {
    return plugin;
  }


}
