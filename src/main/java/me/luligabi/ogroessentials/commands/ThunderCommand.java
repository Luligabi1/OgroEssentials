package me.luligabi.ogroessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.luligabi.ogroessentials.OgroEssentials;
import me.luligabi.ogroessentials.utils.MessageUtils;
import me.luligabi.ogroessentials.utils.Permissions;

public class ThunderCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration cfg = OgroEssentials.plugin.getConfig(); 
    	String prefix = cfg.getString("prefixThunder");
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission(Permissions.COMMAND_THUNDER)) {	
				p.getWorld().setStorm(true);
				p.getWorld().setThundering(true);
				p.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("sucessThunder")
						.replace("&", "§")));
			} else {
				p.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_THUNDER));
			}
		} else {
			sender.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("mustBePlayer")));
		}
		return true;
	}
}