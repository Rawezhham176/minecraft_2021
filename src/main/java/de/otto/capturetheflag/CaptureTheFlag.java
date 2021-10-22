package de.otto.capturetheflag;

import de.otto.capturetheflag.basicconstructplugin.BasicConstructPlugin;
import de.otto.capturetheflag.basicconstructplugin.exceptions.FileException;
import de.otto.capturetheflag.basicconstructplugin.files.YamlFile;
import de.otto.capturetheflag.listener.TeamSelectionListener;
import de.otto.capturetheflag.team.TeamFactory;
import de.otto.capturetheflag.utils.PhaseFactory;
import de.otto.capturetheflag.utils.LocationUtils;
import de.otto.capturetheflag.utils.TeamColor;
import java.util.Objects;

public final class CaptureTheFlag extends BasicConstructPlugin {

  private static CaptureTheFlag instance;
  private YamlFile locations;
  private TeamFactory teamFactory;
  private PhaseFactory phaseFactory;

  private Game game;

  public static CaptureTheFlag getInstance() {
    return instance;
  }

  @Override
  public void onEnable() {
    super.onEnable();
    instance = this;

    setTeamFactory(new TeamFactory());
    setPhaseFactory(new PhaseFactory(this));

    registerTeams();

    getServer().getPluginManager().registerEvents(new TeamSelectionListener(this), this);

    getPhaseFactory().setPhase(getPhaseFactory().getPhaseByName(PhaseName.LOBBY), true);

    Objects.requireNonNull(getCommand("start")).setExecutor(new StartCommand(this));
  }



  private void registerTeams() {
    getTeamFactory()
        .addTeam(TeamColor.BLUE, LocationUtils.TEAM_BLUE_SELECTION, LocationUtils.TEAM_BLUE_SPAWN);
    getTeamFactory()
        .addTeam(TeamColor.RED, LocationUtils.TEAM_RED_SELECTION, LocationUtils.TEAM_RED_SPAWN);
  }

  @Override
  public void setupFiles() throws FileException {
    locations = (YamlFile) loadFile(new YamlFile("locations.yml"));
  }

  public YamlFile getLocations() {
    return locations;
  }

  public void setTeamFactory(TeamFactory teamFactory) {
    this.teamFactory = teamFactory;
  }

  public void setPhaseFactory(PhaseFactory phaseFactory) {
    this.phaseFactory = phaseFactory;
  }

  public PhaseFactory getPhaseFactory() {
    return phaseFactory;
  }

  public TeamFactory getTeamFactory() {
    return teamFactory;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }
}
