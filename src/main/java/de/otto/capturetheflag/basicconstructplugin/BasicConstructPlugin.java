package de.otto.capturetheflag.basicconstructplugin;

import de.otto.capturetheflag.basicconstructplugin.exceptions.FileException;
import java.util.logging.Level;
import de.otto.capturetheflag.basicconstructplugin.files.PluginFile;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BasicConstructPlugin extends JavaPlugin {

  private static BasicConstructPlugin instance;

  public static BasicConstructPlugin getInstance() {
    return instance;
  }

  @Override
  public void onEnable() {
    instance = this;
    try {
      setupFiles();
    } catch (FileException e) {
      e.printStackTrace();
    }
  }

  public void setupFiles() throws FileException {}

  public PluginFile loadFile(PluginFile pluginFile) throws FileException {
    pluginFile.loadFile();
    pluginFile.save();
    return pluginFile;
  }

  public void sendConsoleMessage(String message) {
    getLogger().log(Level.INFO, message);
  }

  public void sendConsoleMessage(LogLevel logLevel, String message) {
    Level level = switch (logLevel) {
      case INFO -> Level.INFO;
      case WARN -> Level.WARNING;
      case ERROR -> Level.SEVERE;
    };
    getLogger().log(level, message);
  }

}
