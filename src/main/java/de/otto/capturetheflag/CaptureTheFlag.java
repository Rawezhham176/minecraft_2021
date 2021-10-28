package de.otto.capturetheflag;

import de.otto.capturetheflag.commands.StartCommand;
import de.otto.capturetheflag.game.Game;
import de.otto.capturetheflag.game.PhaseName;
import de.otto.capturetheflag.team.TeamFactory;
import de.otto.capturetheflag.utils.LocationUtils;
import de.otto.capturetheflag.utils.PhaseFactory;
import de.otto.capturetheflag.utils.TeamColor;
import me.eyetealer.basicconstructplugin.BasicConstructPlugin;
import me.eyetealer.basicconstructplugin.exceptions.FileException;
import me.eyetealer.basicconstructplugin.files.YamlFile;
import org.bukkit.Material;

import java.util.Objects;

/*

TODO:
  Beim Tod Flagge droppen.
  Im Bereich der Flagge soll Timer gestartet werden:
    Wenn Spieler lang genug im Bereich ist, erhält er die Flagge auf dem Kopf.
  Wenn Spieler Flagge auf eigenen Flaggenbereich bringt, soll das Team einen Punkt erhalten und es
  soll überprüft werden, ob das Team jetzt 3 Punkte zum Sieg besitzt.
  Siegmechanik muss eingebaut werden.

 */

public final class CaptureTheFlag extends BasicConstructPlugin {

    private static CaptureTheFlag instance;
    private YamlFile locations;
    private TeamFactory teamFactory;
    private PhaseFactory phaseFactory;

    private Game game;

    public static CaptureTheFlag getInstance() {
        return instance;
    }

    public void onStart() {
        instance = this;

        setTeamFactory(new TeamFactory(this));
        setPhaseFactory(new PhaseFactory(this));

        registerTeams();

        getPhaseFactory().setPhase(PhaseName.LOBBY, true);

        Objects.requireNonNull(getCommand("start")).setExecutor(new StartCommand(this));
    }


    private void registerTeams() {
        getTeamFactory()
                .addTeam(TeamColor.BLUE, LocationUtils.TEAM_BLUE_SELECTION, LocationUtils.TEAM_BLUE_SPAWN, LocationUtils.TEAM_BLUE_FLAG, Material.BLUE_CONCRETE);
        getTeamFactory()
                .addTeam(TeamColor.RED, LocationUtils.TEAM_RED_SELECTION, LocationUtils.TEAM_RED_SPAWN, LocationUtils.TEAM_RED_FLAG, Material.RED_CONCRETE);
    }

    @Override
    public void setupFiles() throws FileException {
        locations = getConfigFile(YamlFile.class, "locations.yml");
        locations.createConfig();
        locations.save();
    }

    public YamlFile getLocations() {
        return locations;
    }

    public PhaseFactory getPhaseFactory() {
        return phaseFactory;
    }

    public void setPhaseFactory(PhaseFactory phaseFactory) {
        this.phaseFactory = phaseFactory;
    }

    public TeamFactory getTeamFactory() {
        return teamFactory;
    }

    public void setTeamFactory(TeamFactory teamFactory) {
        this.teamFactory = teamFactory;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
