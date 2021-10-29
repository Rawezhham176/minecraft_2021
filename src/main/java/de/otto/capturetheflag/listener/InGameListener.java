package de.otto.capturetheflag.listener;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.team.Team;
import de.otto.capturetheflag.team.TeamPlayer;
import de.otto.capturetheflag.utils.Utils;
import java.util.Optional;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerRespawnEvent;

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
    TeamPlayer player = getPlugin().getTeamFactory().getTeamPlayerByPlayer(e.getEntity());
    Optional<Team> teamFlag = getPlugin().getFlagCarrierFactory()
        .getTeamFlagCarriedByTeamPlayer(player);
    if (teamFlag.isPresent()) {
      Team team = teamFlag.get();
      e.getDrops().add(team.getTeamBlockStack());
      getPlugin().getFlagCarrierFactory().removeFlag(team);
    }
  }

  @EventHandler
  public void onPickUp(PlayerAttemptPickupItemEvent e) {
    getPlugin().getTeamFactory().getTeams().stream()
        .filter(team -> team.getTeamBlockStack().equals(e.getItem().getItemStack()))
        .forEach(team -> {
          TeamPlayer teamPlayer = getPlugin().getTeamFactory()
              .getTeamPlayerByPlayer(e.getPlayer());
          if (getPlugin().getFlagCarrierFactory().isPlayerNotCarryingFlag(teamPlayer)) {
            team.takeFlag(teamPlayer);
            getPlugin().getFlagCarrierFactory()
                .takeFlag(team, teamPlayer);
            e.getItem().remove();
          }
        });
  }

  @EventHandler
  public void takeFlag(BlockBreakEvent e) {
    getPlugin().getTeamFactory().getTeams().forEach(team -> {
      if (e.getBlock().getLocation().equals(team.getTeamFlag())) {
        TeamPlayer teamPlayer = getPlugin().getTeamFactory()
            .getTeamPlayerByPlayer(e.getPlayer());
        if (getPlugin().getFlagCarrierFactory().isPlayerNotCarryingFlag(teamPlayer)) {
          if (team != teamPlayer.getPlayerTeam()) {
            team.takeFlag(teamPlayer);
            getPlugin().getFlagCarrierFactory()
                .takeFlag(team, teamPlayer);
            e.setCancelled(false);
          }
        }
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
