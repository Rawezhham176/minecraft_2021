package de.otto.capturetheflag.listener;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.team.Team;
import de.otto.capturetheflag.utils.Utils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class InGameListener extends AbstractGameListener {

  public InGameListener(CaptureTheFlag plugin) {
    super(plugin);
  }

  @EventHandler
  public void onLogin(PlayerLoginEvent e) {
    e.disallow(Result.KICK_WHITELIST,
        MiniMessage.get().parse("<rainbow>Das Spiel läuft bereits</rainbow>"));
  }

  @EventHandler
  public void onRespawn(PlayerRespawnEvent e) {
    Player player = e.getPlayer();
    Utils.setItems(player);
    Team team = getPlugin().getTeamFactory().getTeamByPlayer(player);
    e.setRespawnLocation(team.getTeamSpawn());
  }

  @EventHandler
  public void onDeath(PlayerDeathEvent e) {
    e.setDroppedExp(0);
    e.getDrops().clear();
    getPlugin().getTeamFactory().getTeams().forEach(team -> {
      ItemStack helmet = e.getEntity().getInventory().getHelmet();
      if (helmet != null && helmet.equals(team.getTeamBlockStack())) {
        e.getDrops().add(team.getTeamBlockStack());
      }
    });
  }

  @EventHandler
  public void onPickUp(PlayerAttemptPickupItemEvent e) {
    getPlugin().getTeamFactory().getTeams().forEach(team -> {
      if (team.getTeamBlockStack().equals(e.getItem().getItemStack())) {
        team.takeFlag(e.getPlayer());
      }
    });
  }

  @EventHandler(priority = EventPriority.HIGH)
  public void takeFlag(BlockBreakEvent e) {
    getPlugin().getTeamFactory().getTeams().forEach(team -> {
      if (e.getBlock().getLocation().equals(team.getTeamFlag())) {
        team.takeFlag(e.getPlayer());
        e.setCancelled(false);
      }
    });
  }

  @EventHandler
  public void onDamage(EntityDamageByEntityEvent e) {
    if (e.getEntity() instanceof Player taker && e.getDamager() instanceof Player damager) {
      if (getPlugin().getTeamFactory().getTeamByPlayer(damager).containsPlayer(taker)) {
        e.setCancelled(true);
      }
    }
  }
}
