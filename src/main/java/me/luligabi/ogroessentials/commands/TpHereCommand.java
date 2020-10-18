package me.luligabi.ogroessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.luligabi.ogroessentials.OgroEssentials;
import me.luligabi.ogroessentials.utils.MessageUtils;
import me.luligabi.ogroessentials.utils.Permissions;

public class TpHereCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration cfg = OgroEssentials.plugin.getConfig();
		String prefix = cfg.getString("prefixTpHere");
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission(Permissions.COMMAND_TPHERE)) {
				if(args.length == 0 || args.length >= 2) {
					p.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("incorrectUsageTpHere")));
				} else if(args.length == 1) {
					Player target = Bukkit.getPlayerExact(args[0]);
					if(target == null) {
						p.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("targetNotOnlineTpHere")));
					} else {
						target.teleport(p.getLocation());
						p.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("targetTpHere")
								.replace("&", "§")
								.replace("%target%", target.getName())));
						target.sendMessage(MessageUtils.infoMessage(prefix, cfg.getString("targetTpHere")
								.replace("&", "§")
								.replace("%player%", p.getName())));			
					}
				} else {
					p.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_TPHERE));
				}
			}
		}
		return false;
	}
}
