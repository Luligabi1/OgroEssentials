package me.luligabi.ogroessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import me.luligabi.ogroessentials.OgroEssentials;
import me.luligabi.ogroessentials.utils.MessageUtils;
import me.luligabi.ogroessentials.utils.Permissions;

public class OgroEssentialsCommand implements CommandExecutor {
	
	PluginDescriptionFile pdf = OgroEssentials.plugin.getDescription();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission(Permissions.COMMAND_OGROESSENTIALS)) {
			sender.sendMessage(MessageUtils.infoMessage("OgroEssentials", 
					"\nVersion: " + pdf.getVersion() +
					"\nSpigot API:" + pdf.getAPIVersion() +
					"\nGithub: " + pdf.getWebsite())); 
		} else {
			sender.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_OGROESSENTIALS));
		}
		return true;
	}

}