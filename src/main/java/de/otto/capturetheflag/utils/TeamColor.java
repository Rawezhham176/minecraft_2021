package de.otto.capturetheflag.utils;

import net.md_5.bungee.api.ChatColor;

public enum TeamColor {

  RED(ChatColor.RED),
  BLUE(ChatColor.BLUE);

  private final ChatColor chatColor;

  TeamColor(ChatColor chatColor) {
    this.chatColor = chatColor;
  }

  public ChatColor getChatColor() {
    return chatColor;
  }
}
