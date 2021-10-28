package de.otto.capturetheflag.commands;

import de.otto.capturetheflag.CaptureTheFlag;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DebugCommand implements CommandExecutor {

  private final CaptureTheFlag plugin;

  public DebugCommand(CaptureTheFlag plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
      @NotNull String label, @NotNull String[] args) {
    if (sender instanceof ConsoleCommandSender || (sender instanceof Player && sender.isOp())) {

      boolean newState = !getPlugin().isDebugMode();
      getPlugin().setDebugMode(newState);
      sender.sendMessage(MiniMessage.get().parse("<gold>DebugMode wurde " + (newState ? "<green>eingeschaltet" : "<red>ausgeschaltet") + "<gold>."));

    }

    return false;
  }

  public CaptureTheFlag getPlugin() {
    return plugin;
  }
}
