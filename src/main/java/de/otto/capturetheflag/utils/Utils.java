package de.otto.capturetheflag.utils;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utils {

  public static void setItems(Player player) {

    player.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD, 1));
    player.getInventory().setItem(1, new ItemStack(Material.BOW, 1));
    player.getInventory().setItem(7, new ItemStack(Material.ARROW, 16));
    player.getInventory().setItem(8, new ItemStack(Material.GOLDEN_CARROT, 64));

    player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
    player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
    player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
    player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));
  }

  public static void resetPlayer(Player player) {
    player.getInventory().clear();
    player.setGameMode(GameMode.SURVIVAL);
    player.setExp(0);
    player.setLevel(0);
    player.setHealth(20);
    player.setFoodLevel(20);
  }
}
