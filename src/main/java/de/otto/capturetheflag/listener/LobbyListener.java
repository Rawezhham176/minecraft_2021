package de.otto.capturetheflag.listener;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.team.Team;
import de.otto.capturetheflag.utils.LocationUtils;
import de.otto.capturetheflag.utils.TeamColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
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
    Location teamSelectionBlue = LocationUtils.TEAM_BLUE_SELECTION;
    Location teamSelectionRed = LocationUtils.TEAM_RED_SELECTION;
    Team teamBlue = getPlugin().getTeamFactory().getTeamByColor(TeamColor.BLUE);
    Team teamRed = getPlugin().getTeamFactory().getTeamByColor(TeamColor.RED);
    if (LocationUtils.isPlayerInLocationRange(player, teamSelectionBlue, 3)) {
      if (!teamBlue.containsPlayer(player)) {
        teamBlue.addPlayer(player);
        teamRed.removePlayer(player);
        player.sendMessage("§bDu bist jetzt im Team §1BLAU");
      }
    } else if (LocationUtils.isPlayerInLocationRange(player, teamSelectionRed, 3)) {
      if (!teamRed.containsPlayer(player)) {
        teamRed.addPlayer(player);
        teamBlue.removePlayer(player);
        player.sendMessage("§bDu bist jetzt im Team §4ROT");
      }
    }
  }

}
