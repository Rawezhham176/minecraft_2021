package de.otto.capturetheflag.utils;

import de.otto.capturetheflag.CaptureTheFlag;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class LocationUtils {

  public static Location LOBBY_SPAWN;
  public static Location TEAM_BLUE_SELECTION;
  public static Location TEAM_RED_SELECTION;
  public static Location TEAM_BLUE_SPAWN;
  public static Location TEAM_RED_SPAWN;
  public static Location TEAM_BLUE_FLAG;
  public static Location TEAM_RED_FLAG;

  static {
    TEAM_BLUE_SELECTION = getLocation("Teams.Blue.Selection");
    TEAM_RED_SELECTION = getLocation("Teams.Red.Selection");
    TEAM_BLUE_SPAWN = getLocationWithLooking("Teams.Blue.Spawn");
    TEAM_RED_SPAWN = getLocationWithLooking("Teams.Red.Spawn");
    TEAM_BLUE_FLAG = getLocation("Teams.Blue.Flag");
    TEAM_RED_FLAG = getLocation("Teams.Red.Flag");
    LOBBY_SPAWN = getLocationWithLooking("LobbySpawn");
  }

  private static Location getLocation(String locationName) {
    String path = getLocationPath(locationName);
    double x = CaptureTheFlag.getInstance().getLocations().getConfig().getDouble(path + ".X");
    double y = CaptureTheFlag.getInstance().getLocations().getConfig().getDouble(path + ".Y");
    double z = CaptureTheFlag.getInstance().getLocations().getConfig().getDouble(path + ".Z");
    String worldName = CaptureTheFlag.getInstance().getLocations().getConfig()
        .getString(path + ".World");
    assert worldName != null;
    World world = Bukkit.getWorld(worldName);
    return new Location(world, x, y, z);
  }

  private static Location getLocationWithLooking(String locationName) {
    Location location = getLocation(locationName);
    String path = getLocationPath(locationName);
    float yaw = CaptureTheFlag.getInstance().getLocations().getConfig().getLong(path + ".Yaw");
    float pitch = CaptureTheFlag.getInstance().getLocations().getConfig().getLong(path + ".Pitch");
    location.setYaw(yaw);
    location.setPitch(pitch);
    return location;
  }

  private static String getLocationPath(String path) {
    return "Locations." + path;
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
