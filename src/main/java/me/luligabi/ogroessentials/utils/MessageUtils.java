package me.luligabi.ogroessentials.utils;

import me.luligabi.ogroessentials.OgroEssentials;

public class MessageUtils {
	
	public static String sucessMessage(String prefix, String message) {
		return OgroEssentials.plugin.getConfig().getString("sucessMessage")
				.replace("&", "§")
				.replace("%prefix%", prefix)
				.replace("%message%", message);
	}
	public static String errorMessage(String prefix, String message) {
		return OgroEssentials.plugin.getConfig().getString("errorMessage")
				.replace("&", "§")
				.replace("%prefix%", prefix)
				.replace("%message%", message);
	}
	public static String infoMessage(String prefix, String message) {
		return OgroEssentials.plugin.getConfig().getString("infoMessage")
				.replace("&", "§")
				.replace("%prefix%", prefix)
				.replace("%message%", message);
	}
	public static String permissionMessage(String permission) {
		return OgroEssentials.plugin.getConfig().getString("infoMessage")
				.replace("&", "§")
				.replace("%permission%", permission);
	}
}