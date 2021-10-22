package de.otto.capturetheflag;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class LocationUtils {

  public static Location TEAM_SELECTION_BLUE;
  public static Location TEAM_SELECTION_RED;

  static {
    TEAM_SELECTION_BLUE = getLocation("TeamSelection.Blue");
    TEAM_SELECTION_RED = getLocation("TeamSelection.Red");
  }

  private static Location getLocation(String locationName) {
    int x = CaptureTheFlag.getInstance().getLocations().getInt(locationName + ".X");
    int y = CaptureTheFlag.getInstance().getLocations().getInt(locationName + ".Y");
    int z = CaptureTheFlag.getInstance().getLocations().getInt(locationName + ".Z");
    String worldName = CaptureTheFlag.getInstance().getLocations()
        .getString(locationName + ".World");
    assert worldName != null;
    World world = Bukkit.getWorld(worldName);
    return new Location(world, x, y, z);
  }

  public static boolean isPlayerInLocationRange(Player player, Location center, int range) {
    if (range % 2 == 0) {
      return false;
    }
    Location playerLocation = player.getLocation().getBlock().getRelative(BlockFace.DOWN)
        .getLocation();
    Location inRange = center.clone();
    range = (range - 1) / 2;
    for (int x = center.getBlockX() - range; x <= center.getBlockX() + range; x++) {
      for (int y = center.getBlockY() - range; y <= center.getBlockY() + range; y++) {
        for (int z = center.getBlockZ() - range; z <= center.getBlockZ() + range; z++) {
          if (playerLocation.equals(inRange.set(x, y, z))) {
            return true;
          }
        }
      }
    }
    return false;
  }

}
