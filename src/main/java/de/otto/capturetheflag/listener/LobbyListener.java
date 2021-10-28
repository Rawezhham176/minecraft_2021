package de.otto.capturetheflag.listener;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.utils.LocationUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class LobbyListener extends AbstractGameListener {

  public LobbyListener(CaptureTheFlag plugin) {
    super(plugin);
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
  public void onMove(PlayerMoveEvent e) {
    Player player = e.getPlayer();

    getPlugin().getTeamFactory().getTeams().forEach(team -> {
      if (LocationUtils.isPlayerInLocationRange(player, team.getTeamSelection(), 3)) {
        if (!team.containsPlayer(player)) {
          team.addPlayer(player);
          getPlugin().getTeamFactory().otherTeams(team)
              .forEach(other -> other.removePlayer(player));
          player.sendMessage(
              "§bDu bist jetzt im Team " + team.getColor().getChatColor() + team.getColor());
        }
      }
    });
  }

  @EventHandler
  public void onExp(PlayerExpChangeEvent e) {
    e.setAmount(0);
  }

}
