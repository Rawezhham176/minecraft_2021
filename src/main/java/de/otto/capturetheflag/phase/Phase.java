package de.otto.capturetheflag.phase;

import de.otto.capturetheflag.CaptureTheFlag;
import de.otto.capturetheflag.PhaseName;
import de.otto.capturetheflag.listener.AbstractGameListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.event.Listener;

public class Phase {

  private final CaptureTheFlag plugin;
  private final PhaseName phaseName;
  private final List<AbstractGameListener> phaseListener;

  public Phase(CaptureTheFlag plugin, PhaseName phaseName, Class<? extends AbstractGameListener>[] phaseListenerClasses) {
    this.plugin = plugin;
    this.phaseName = phaseName;
    this.phaseListener = new ArrayList<>();
    loadListener(phaseListenerClasses);
  }

  private void loadListener(Class<? extends AbstractGameListener>[] phaseListenerClasses) {

    Arrays.stream(phaseListenerClasses).forEach(phaseListener -> {
      try {
        AbstractGameListener listener = phaseListener.getDeclaredConstructor(CaptureTheFlag.class)
            .newInstance(getPlugin());
        getPhaseListener().add(listener);
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        e.printStackTrace();
      }
    });
  }

  public PhaseName getPhaseName() {
    return phaseName;
  }

  public List<AbstractGameListener> getPhaseListener() {
    return phaseListener;
  }

  public CaptureTheFlag getPlugin() {
    return plugin;
  }
}
