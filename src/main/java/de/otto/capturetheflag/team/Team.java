package de.otto.capturetheflag.team;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.utils.LocationUtils;
import de.otto.capturetheflag.utils.TeamColor;
import de.otto.capturetheflag.utils.Utils;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.logging.log4j.util.SystemPropertiesPropertySource;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Team {

  private final CaptureTheFlag plugin;
  private final TeamColor color;
  private final Location teamSelection;
  private final Location teamSpawn;
  private final Location teamFlag;
  private final Location teamFlagRange;
  private final Material teamBlock;
  private int score;
  private int task;

  public Team(CaptureTheFlag plugin, TeamColor color, Location teamSelection, Location teamSpawn,
      Location teamFlag, Material teamBlock) {
    this.plugin = plugin;
    this.color = color;
    this.teamSelection = teamSelection;
    this.teamSpawn = teamSpawn;
    this.teamFlag = teamFlag;
    this.teamFlagRange = teamFlag.clone().add(0, -4, 0);
    this.teamBlock = teamBlock;
  }

  public void teleportAllPlayersToTeamSpawn() {
    getPlayers().forEach(player -> player.teleport(getTeamSpawn()));
  }

//  public void teleportPlayerToTeamSpawn(Player player) {
//    player.teleport(getTeamSpawn());
//  }

  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  public boolean containsPlayer(Player player) {
    return getPlayers().contains(player);
  }

  public TeamColor getColor() {
    return color;
  }

  public List<Player> getPlayers() {
    return plugin.getTeamFactory().getTeamPlayers().stream()
        .filter(teamPlayer -> teamPlayer.getPlayerTeam() == this).map(TeamPlayer::getPlayer)
        .collect(
            Collectors.toList());
  }

  public Location getTeamSelection() {
    return teamSelection;
  }

  public Location getTeamSpawn() {
    return teamSpawn;
  }

  public Location getTeamFlag() {
    return teamFlag;
  }

  public Location getTeamFlagRange() {
    return teamFlagRange;
  }

  public Material getTeamBlock() {
    return teamBlock;
  }

  public ItemStack getTeamBlockStack() {
    ItemStack itemStack = new ItemStack(getTeamBlock(), 1);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.displayName(
        MiniMessage.get().parse(getColor().getChatColor() + getColor().name() + " Flag"));
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  public void takeFlag(TeamPlayer player) {
    player.getPlayer().getInventory().setHelmet(getTeamBlockStack());

    Bukkit.getServer().sendMessage(MiniMessage.get().parse(
        player.getPlayerTeam().getColor().getChatColor()
            + player.getPlayer().getName()
            + "<yellow> hat die Flagge von Team "
            + getColor().getChatColor()
            + getColor().name() + "<yellow> an sich gerissen."));
  }

  public void startCheckFlag() {
    task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
      Optional<TeamPlayer> playerByTeamFlag = plugin.getFlagCarrierFactory()
          .getPlayerByTeamFlag(this);
      if (playerByTeamFlag.isPresent()) {
        TeamPlayer teamPlayer = playerByTeamFlag.get();
        if (LocationUtils.isPlayerInLocationRange(teamPlayer.getPlayer(), this.getTeamFlagRange(),
            3)) {
          if (teamPlayer.getPlayerTeam() == this) {
            returnFlag(teamPlayer.getPlayer());
            respawnFlag();
          }
        }
      }

      plugin.getFlagCarrierFactory()
          .getTeamMembersWithEnemyTeamFlag(this).forEach(teamPlayerEntry -> {
        TeamPlayer teamPlayer = teamPlayerEntry.getValue();
        if (LocationUtils.isPlayerInLocationRange(teamPlayer.getPlayer(), this.getTeamFlagRange(),
            3)) {
          Team team = teamPlayerEntry.getKey();
          if (teamPlayer.getPlayerTeam() == this) {
            addScore(teamPlayerEntry);
            team.respawnFlag();
          }
        }
      });

    }, 1, 1);
  }

  public int getScore() {
    return score;
  }

  public void respawnFlag() {
    getTeamFlag().getBlock().setType(getTeamBlock());
  }

  public void shutdown() {
    Bukkit.getScheduler().cancelTask(task);
  }

  public void addScore(Entry<Team, TeamPlayer> teamPlayerEntry) {
    Player player = teamPlayerEntry.getValue().getPlayer();
    Team team = teamPlayerEntry.getKey();
    Utils.setHelmet(player);
    plugin.getFlagCarrierFactory().removeFlag(team);
    this.score += 1;
    Bukkit.getServer().sendMessage(MiniMessage.get().parse(
        getColor().getChatColor()
            + player.getName()
            + "<yellow> hat einen Punkt für Team "
            + getColor().getChatColor()
            + getColor().name() + "<yellow> gemacht."));
    plugin.getGame().checkScore(this);
  }

  private void returnFlag(Player player) {
    Utils.setHelmet(player);
    plugin.getFlagCarrierFactory().removeFlag(plugin.getTeamFactory().getTeamByPlayer(player));
    Bukkit.getServer().sendMessage(MiniMessage.get().parse(
        getColor().getChatColor()
            + player.getName()
            + "<yellow> die Flagge von Team "
            + getColor().getChatColor()
            + getColor().name() + "<yellow> zurückgebracht."));

  }

  public void equipAllPlayers() {
    getPlayers().forEach(player -> {
      Utils.resetPlayer(player);
      Utils.setItems(player);
    });
  }

}
