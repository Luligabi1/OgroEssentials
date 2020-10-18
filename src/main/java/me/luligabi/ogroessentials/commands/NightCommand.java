package me.luligabi.ogroessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.luligabi.ogroessentials.OgroEssentials;
import me.luligabi.ogroessentials.utils.MessageUtils;
import me.luligabi.ogroessentials.utils.Permissions;

public class NightCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration cfg = OgroEssentials.plugin.getConfig(); 
    	String prefix = cfg.getString("prefixNight");
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission(Permissions.COMMAND_NIGHT)) {		
				p.getWorld().setTime(18000);
				p.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("sucessNight")));
			} else {
				p.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_NIGHT));
			}
		} else {
			sender.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("mustBePlayer")));
		}
		return true;
	}
}
