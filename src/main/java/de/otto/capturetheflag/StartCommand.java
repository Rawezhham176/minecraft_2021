package de.otto.capturetheflag;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StartCommand implements CommandExecutor {

  private final CaptureTheFlag plugin;


  public StartCommand(CaptureTheFlag plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
      @NotNull String label, @NotNull String[] args) {
    if (sender instanceof ConsoleCommandSender || (sender instanceof Player && sender.isOp())) {

      if (getPlugin().getGame() == null) {
        Game game = new Game(plugin);
        getPlugin().setGame(game);
        game.start();
        sender.sendMessage("§bDas Spiel wurde gestartet.");
      } else {
        sender.sendMessage("§bDas Spiel läuft bereit!");
      }

    }

    return false;
  }

  public CaptureTheFlag getPlugin() {
    return plugin;
  }
}
