package de.otto.capturetheflag.utils;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.game.PhaseName;
import de.otto.capturetheflag.listener.AbstractGameListener;
import de.otto.capturetheflag.listener.InGameListener;
import de.otto.capturetheflag.listener.LobbyListener;
import de.otto.capturetheflag.phase.Phase;
import java.util.ArrayList;
import java.util.List;

public class PhaseFactory {

  private final CaptureTheFlag plugin;
  private final List<Phase> phases = new ArrayList<>();

  public PhaseFactory(CaptureTheFlag plugin) {
    this.plugin = plugin;
    addPhase(PhaseName.LOBBY, LobbyListener.class);
    addPhase(PhaseName.INGAME, InGameListener.class);
  }

  @SafeVarargs
  private void addPhase(PhaseName phaseName,
      Class<? extends AbstractGameListener>... phaseListenerClasses) {
    getPhases().add(new Phase(getPlugin(), phaseName, phaseListenerClasses));
  }

  public void setPhase(PhaseName phaseName, boolean start) {
    getByName(phaseName).getPhaseListener()
        .forEach(phaseListener -> phaseListener.registerListener(start));
  }

  public Phase getByName(PhaseName phaseName) {
    return phases.stream().filter(phase -> phase.getPhaseName() == phaseName).findFirst()
        .orElseThrow();
  }

  public List<Phase> getPhases() {
    return phases;
  }

  public CaptureTheFlag getPlugin() {
    return plugin;
  }
}
