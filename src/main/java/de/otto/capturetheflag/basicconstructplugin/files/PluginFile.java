package de.otto.capturetheflag.basicconstructplugin.files;

import de.otto.capturetheflag.basicconstructplugin.exceptions.CannotLoadFileException;
import de.otto.capturetheflag.basicconstructplugin.exceptions.CannotSaveFileException;

public interface PluginFile {

  /**
   * Creates and loads YamlFile
   *
   */
  void loadFile() throws CannotLoadFileException;

  /**
   * Saves YamlFile
   *
   */
  void save() throws CannotSaveFileException;

}
