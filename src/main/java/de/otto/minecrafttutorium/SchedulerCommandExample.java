package de.otto.minecrafttutorium;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class SchedulerCommandExample implements CommandExecutor {

    MinecraftTutorium plugin;

    public SchedulerCommandExample(MinecraftTutorium minecraftTutorium) {
        plugin = minecraftTutorium;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("boom") && sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            Location playerLocation = player.getLocation();


            playerLocation.getWorld().playEffect(playerLocation, Effect.ENDER_SIGNAL, 2003);


            player.playSound(playerLocation, Sound.ENTITY_PLAYER_LEVELUP, 1, 10);

            sender.sendMessage(ChatColor.RED + "Exploding in 5 seconds...");

            new BukkitRunnable() {
                @Override
                public void run() {

                    // Another, more powerful way to build messages, see: https://docs.adventure.kyori.net/minimessage#usage
                    Bukkit.getServer().sendMessage(MiniMessage.get().parse("<rainbow>* Boooom *</rainbow>"));

                    // 4F is the same power like TNT; See java Docs =)
                    playerLocation.createExplosion(4F);
                }
            }.runTaskLater(plugin, 20 * 5);

        }
        return true;
    }
}