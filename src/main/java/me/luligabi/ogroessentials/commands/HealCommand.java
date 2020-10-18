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

public class HealCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration cfg = OgroEssentials.plugin.getConfig(); 
		String prefix = cfg.getString("prefixHeal");
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission(Permissions.COMMAND_HEAL)) {
				if(args.length == 0) {
					p.setHealth(20f);
					p.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("sucessHeal")));
				} else if(args.length == 1) {
					if(p.hasPermission(Permissions.COMMAND_HEAL_OTHERS)) {
						Player target = Bukkit.getPlayerExact(args[0]);
						if(target == null) {
							p.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("targetNotOnlineHeal")));
						} else {
							target.setHealth(20f);
							p.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("targetHeal")
									.replace("&", "§")
									.replace("%target%", target.getName())));
							target.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("targetHeal2")
									.replace("&", "§")
									.replace("%player%", p.getName())));
						}
					} else {
						p.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_HEAL_OTHERS));
					}
				} else if(args.length >= 2) {
					p.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("incorrectUsageHeal")));
				}
			} else {
				p.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_HEAL));
			}
		}
		return false;
	}
}