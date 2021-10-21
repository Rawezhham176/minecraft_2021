package de.otto.capturetheflag;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtils {

  public static Location TEAMSELECTION_BLUE;
  public static Location TEAMSELECTION_RED;

  static {
    TEAMSELECTION_BLUE = getLocation("TeamSelection.Blue");
    TEAMSELECTION_RED = getLocation("TeamSelection.Red");
  }

  private static Location getLocation(String locationName) {
    int x = CaptureTheFlag.getInstance().getLocations().getInt(locationName + ".X");
    int y = CaptureTheFlag.getInstance().getLocations().getInt(locationName + ".Y");
    int z = CaptureTheFlag.getInstance().getLocations().getInt(locationName + ".Z");
    String worldName = CaptureTheFlag.getInstance().getLocations().getString(locationName + ".World");
    assert worldName != null;
    World world = Bukkit.getWorld(worldName);
    return new Location(world, x, y, z);
  }

  public static boolean locationEquals(Location firstLocation, Location secondLocation) {
    return firstLocation.getX() == secondLocation.getX()
        && firstLocation.getY() == secondLocation.getZ()
        && firstLocation.getZ() == secondLocation.getZ()
        && firstLocation.getWorld() == secondLocation.getWorld();
  }

}
