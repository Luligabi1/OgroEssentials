package me.luligabi.ogroessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.luligabi.ogroessentials.OgroEssentials;
import me.luligabi.ogroessentials.utils.MessageUtils;
import me.luligabi.ogroessentials.utils.Permissions;

public class DayCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration cfg = OgroEssentials.plugin.getConfig(); 
    	String prefix = cfg.getString("prefixDay");
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission(Permissions.COMMAND_DAY)) {		
				p.getWorld().setTime(1000);
				p.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("sucessDay")));
			} else {
				p.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_DAY));
			}
		} else {
			sender.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("mustBePlayer")));
		}
		return true;
	}
}
