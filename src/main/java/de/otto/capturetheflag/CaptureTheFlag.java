package de.otto.capturetheflag;

import de.otto.capturetheflag.basicconstructplugin.BasicConstructPlugin;
import de.otto.capturetheflag.basicconstructplugin.exceptions.FileException;
import de.otto.capturetheflag.basicconstructplugin.files.YamlFile;
import de.otto.capturetheflag.listener.TeamSelectionListener;
import de.otto.capturetheflag.team.TeamFactory;
import de.otto.capturetheflag.utils.LocationUtils;
import de.otto.capturetheflag.utils.TeamColor;

public final class CaptureTheFlag extends BasicConstructPlugin {

  private YamlFile locations;

  private static CaptureTheFlag instance;
  private TeamFactory teamFactory;


  @Override
  public void onEnable() {
    super.onEnable();
    instance = this;

    teamFactory = new TeamFactory();

    registerTeams();

    getServer().getPluginManager().registerEvents(new TeamSelectionListener(this), this);
  }

  private void registerTeams() {
    getTeamFactory().addTeam(TeamColor.BLUE, LocationUtils.TEAM_BLUE_SELECTION, LocationUtils.TEAM_BLUE_SPAWN);
    getTeamFactory().addTeam(TeamColor.RED, LocationUtils.TEAM_RED_SELECTION, LocationUtils.TEAM_RED_SPAWN);
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

  public TeamFactory getTeamFactory() {
    return teamFactory;
  }
}
