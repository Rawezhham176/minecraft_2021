package de.otto.capturetheflag.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Starterkit {
    public static void setItems(Player player) {
        ItemStack ironSword = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemStack arrows = new ItemStack(Material.ARROW, 16);
        ItemStack food = new ItemStack(Material.GOLDEN_CARROT, 64);

        ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);


        player.getInventory().setItem(0, ironSword);
        player.getInventory().setItem(1, bow);
        player.getInventory().setItem(7, arrows);
        player.getInventory().setItem(8, food);

        player.getInventory().setHelmet(helmet);
        player.getInventory().setHelmet(chestplate);
        player.getInventory().setHelmet(leggings);
        player.getInventory().setHelmet(boots);
    }
}
