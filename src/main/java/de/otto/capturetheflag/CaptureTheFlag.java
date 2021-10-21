package de.otto.capturetheflag;

import de.otto.capturetheflag.basicconstructplugin.BasicConstructPlugin;
import de.otto.capturetheflag.basicconstructplugin.exceptions.FileException;
import de.otto.capturetheflag.basicconstructplugin.files.YamlFile;

public final class CaptureTheFlag extends BasicConstructPlugin {

  private YamlFile locations;

  private static CaptureTheFlag instance;


  @Override
  public void onEnable() {
    super.onEnable();
    instance = this;

    getServer().getPluginManager().registerEvents(new TeamSelectionListener(this), this);
  }

  @Override
  public void setupFiles() throws FileException {
    locations = (YamlFile) loadFile(new YamlFile("locations.yml"));
  }

  public static CaptureTheFlag getInstance() {
    return instance;
  }

  public YamlFile getLocations() {
    return locations;
  }
}
