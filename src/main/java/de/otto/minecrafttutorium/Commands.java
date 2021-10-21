package de.otto.minecrafttutorium;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("heal") && sender instanceof Player) {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.RED + "Please include how much you want to be healed by");
                return true;
            } else if (args.length == 1) {
                try {
                    double playerHealth = ((Player) sender).getHealth();
                    double healthToAdd = Double.parseDouble(args[0]);
                    if (playerHealth < 20) {
                        ((Player) sender).setHealth(playerHealth + healthToAdd);
                        ((Player) sender).sendMessage(ChatColor.GRAY + "You have been healed for " +
                                ChatColor.GREEN + healthToAdd + " health");
                        return true;
                    } else {
                        ((Player) sender).sendMessage(ChatColor.YELLOW + "If you take some damage...i could heal you!");
                        return true;
                    }
                } catch (NumberFormatException e) {
                    ((Player) sender).sendMessage(ChatColor.RED + "Please input a real number!");
                    return true;
                }
            }
        }
        sender.sendMessage( ChatColor.RED + "Only Players can call this command");
        return true;
    }
}
