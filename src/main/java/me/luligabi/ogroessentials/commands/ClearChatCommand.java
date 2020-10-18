package me.luligabi.ogroessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import me.luligabi.ogroessentials.OgroEssentials;
import me.luligabi.ogroessentials.utils.MessageUtils;
import me.luligabi.ogroessentials.utils.Permissions;

public class ClearChatCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	FileConfiguration cfg = OgroEssentials.plugin.getConfig(); 
    	String prefix = cfg.getString("prefixClearChat");
		
		if(sender.hasPermission(Permissions.COMMAND_CLEARCHAT)) {
			for(int i = 0;i<=100;i++) {
				Bukkit.broadcastMessage(" "); //TODO: consertar spam no console
			}
			sender.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("sucessClearChat")));
			Bukkit.broadcastMessage(MessageUtils.infoMessage(prefix, cfg.getString("sucessClearChat2")));
		} else {
			sender.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_CLEARCHAT));
		}
		return true;
	}

}