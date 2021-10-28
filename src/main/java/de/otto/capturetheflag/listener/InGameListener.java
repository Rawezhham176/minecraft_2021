package de.otto.capturetheflag.listener;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.team.Team;
import de.otto.capturetheflag.utils.Starterkit;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerRespawnEvent;

public class InGameListener extends AbstractGameListener {

  public InGameListener(CaptureTheFlag plugin) {
    super(plugin);
  }

  @EventHandler
  public void onLogin(PlayerLoginEvent e) {
    e.disallow(Result.KICK_WHITELIST, MiniMessage.get().parse("<rainbow>Das Spiel läuft bereits</rainbow>"));
  }

  @EventHandler
  public void onRespawn(PlayerRespawnEvent e) {
    Player player = e.getPlayer();
    Starterkit.setItems(player);
    Team team = getPlugin().getTeamFactory().getTeamByPlayer(player);
    e.setRespawnLocation(team.getTeamSpawn());
  }

  @EventHandler
  public void onDeath(PlayerDeathEvent e) {
    e.setDroppedExp(0);
    e.getDrops().clear();
  }
}
