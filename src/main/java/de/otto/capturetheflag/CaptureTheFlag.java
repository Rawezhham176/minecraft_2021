package de.otto.capturetheflag;

import de.otto.capturetheflag.commands.DebugCommand;
import de.otto.capturetheflag.commands.StartCommand;
import de.otto.capturetheflag.flagcarrier.FlagCarrierFactory;
import de.otto.capturetheflag.game.Game;
import de.otto.capturetheflag.game.PhaseName;
import de.otto.capturetheflag.team.TeamFactory;
import de.otto.capturetheflag.utils.LocationUtils;
import de.otto.capturetheflag.utils.PhaseFactory;
import de.otto.capturetheflag.utils.TeamColor;
import java.util.Objects;
import me.eyetealer.basicconstructplugin.BasicConstructPlugin;
import me.eyetealer.basicconstructplugin.exceptions.CannotSaveFileException;
import me.eyetealer.basicconstructplugin.exceptions.FileException;
import me.eyetealer.basicconstructplugin.files.YamlFile;
import org.bukkit.Material;

public final class CaptureTheFlag extends BasicConstructPlugin {

  private static CaptureTheFlag instance;
  private YamlFile locationsFile;
  private YamlFile configFile;
  private TeamFactory teamFactory;
  private PhaseFactory phaseFactory;
  private FlagCarrierFactory flagCarrierFactory;

  private boolean debugMode;

  private Game game;

  public static CaptureTheFlag getInstance() {
    return instance;
  }

  public void onStart() {
    instance = this;

    teamFactory = new TeamFactory(this);
    phaseFactory = new PhaseFactory(this);
    flagCarrierFactory = new FlagCarrierFactory(this);

    registerTeams();

    phaseFactory.setPhase(PhaseName.LOBBY, true);

    Objects.requireNonNull(getCommand("start")).setExecutor(new StartCommand(this));
    Objects.requireNonNull(getCommand("debug")).setExecutor(new DebugCommand(this));
  }

  public void onStop() {
    try {
      configFile.getConfig().set("DebugMode", debugMode);
      configFile.save();
    } catch (CannotSaveFileException e) {
      e.printStackTrace();
    }
  }

  private void registerTeams() {
    teamFactory.addTeam(TeamColor.BLUE, LocationUtils.TEAM_BLUE_SELECTION,
        LocationUtils.TEAM_BLUE_SPAWN, LocationUtils.TEAM_BLUE_FLAG, Material.BLUE_CONCRETE);
    teamFactory.addTeam(TeamColor.RED, LocationUtils.TEAM_RED_SELECTION,
        LocationUtils.TEAM_RED_SPAWN, LocationUtils.TEAM_RED_FLAG, Material.RED_CONCRETE);
  }

  @Override
  public void setupFiles() throws FileException {
    locationsFile = getConfigFile(YamlFile.class, "locations.yml");
    locationsFile.createConfig();
    locationsFile.save();
    configFile = getConfigFile(YamlFile.class, "config.yml");
    configFile.createConfig();
    configFile.save();
    debugMode = configFile.getConfig().getBoolean("DebugMode");
  }

  public YamlFile getLocationsFile() {
    return locationsFile;
  }

  public PhaseFactory getPhaseFactory() {
    return phaseFactory;
  }

  public TeamFactory getTeamFactory() {
    return teamFactory;
  }

  public FlagCarrierFactory getFlagCarrierFactory() {
    return flagCarrierFactory;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public boolean isDebugMode() {
    return debugMode;
  }

  public void setDebugMode(boolean debugMode) {
    this.debugMode = debugMode;
  }
}
