package de.otto.capturetheflag.team;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.utils.Starterkit;
import de.otto.capturetheflag.utils.TeamColor;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
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
  private final Material teamBlock;
  private final List<Player> players;
  private int score;

  public Team(CaptureTheFlag plugin, TeamColor color, Location teamSelection, Location teamSpawn,
      Location teamFlag, Material teamBlock) {
    this.plugin = plugin;
    this.color = color;
    this.teamSelection = teamSelection;
    this.teamSpawn = teamSpawn;
    this.teamFlag = teamFlag;
    this.teamBlock = teamBlock;
    this.players = new ArrayList<>();
  }

  public void teleportAllPlayersToTeamSpawn() {
    getPlayers().forEach(player -> player.teleport(getTeamSpawn()));
  }

  public void teleportPlayerToTeamSpawn(Player player) {
    player.teleport(getTeamSpawn());
  }

  public void addPlayer(Player player) {
    getPlayers().add(player);
  }

  public void removePlayer(Player player) {
    getPlayers().remove(player);
  }

  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  public boolean containsPlayer(Player player) {
    return getPlayers().contains(player);
  }

  public TeamColor getColor() {
    return color;
  }

  public List<Player> getPlayers() {
    return players;
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

  public void takeFlag(Player player) {
    player.getInventory().setHelmet(getTeamBlockStack());
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void addScore() {
    this.score += 1;
    plugin.getGame().checkScore(this);
  }

  public void equipAllPlayers() {
    players.forEach(player -> {
      player.getInventory().clear();
      player.setGameMode(GameMode.SURVIVAL);
      player.setExp(0);
      player.setLevel(0);
      player.setHealth(20);
      player.setFoodLevel(20);
      Starterkit.setItems(player);
    });
  }

}
