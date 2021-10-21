package de.otto.capturetheflag.basicconstructplugin.files;

import de.otto.capturetheflag.basicconstructplugin.BasicConstructPlugin;
import java.io.File;
import java.io.IOException;
import de.otto.capturetheflag.basicconstructplugin.exceptions.CannotCreateFileException;
import de.otto.capturetheflag.basicconstructplugin.exceptions.CannotLoadConfigFileException;
import de.otto.capturetheflag.basicconstructplugin.exceptions.CannotLoadFileException;
import de.otto.capturetheflag.basicconstructplugin.exceptions.CannotSaveFileException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class YamlFile extends YamlConfiguration implements PluginFile {

  private final JavaPlugin plugin = BasicConstructPlugin.getInstance();

  private final String directory;
  private final String fileName;

  private File configFile;

  /**
   * Create YamlFile in a custom directory
   *
   * @param customDirectory - current directory is where the server jar is saved
   * @param fileName        - fileName
   */
  public YamlFile(String customDirectory,
      String fileName) {
    this.directory = customDirectory;
    this.fileName = fileName;
  }

  /**
   * Create YamlFile in a custom directory
   *
   * @param fileName - fileName
   */
  public YamlFile(String fileName) {
    this.directory = "plugins/" + getPlugin().getName();
    this.fileName = fileName;
  }


  @Override
  public void loadFile() throws CannotLoadFileException {

    configFile = new File(directory, fileName);

    if (!configFile.getParentFile().exists()) {
      if (!configFile.getParentFile().mkdirs()) {
        return;
      }
    }

    if (!configFile.exists()) {
      if (getPlugin().getResource(fileName) != null) {
        getPlugin().saveResource(fileName, false);
      } else {
        try {
          //noinspection ResultOfMethodCallIgnored
          configFile.createNewFile();
        } catch (IOException e) {
          throw new CannotCreateFileException(e.fillInStackTrace());
        }
      }
    }

    try {
      this.load(configFile);
    } catch (IOException | InvalidConfigurationException e) {
      throw new CannotLoadConfigFileException(e.fillInStackTrace());
    }

  }

  @Override
  public void save() throws CannotSaveFileException {
    try {
      this.save(configFile);
    } catch (IOException e) {
      throw new CannotSaveFileException(e.fillInStackTrace());
    }
  }

  private JavaPlugin getPlugin() {
    return plugin;
  }
}
