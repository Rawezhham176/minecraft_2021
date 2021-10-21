package de.otto.minecrafttutorium;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Events implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        Player player = event.getPlayer();

        if(material == Material.GRASS_BLOCK){
            event.setCancelled(true);
            block.setType(Material.LAVA);
        }
        else {
            event.setExpToDrop(1000000);
            player.sendMessage("Found 1000000 XP");
        }
    }

    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player)
        {
            ((Player) event.getDamager()).damage(2);
            ((Player) event.getDamager()).sendMessage(ChatColor.DARK_AQUA + "Dont hit stuff :(");
        }
    }

}
