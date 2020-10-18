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

public class FeedCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration cfg = OgroEssentials.plugin.getConfig(); 
		String prefix = cfg.getString("prefixFeed");
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission(Permissions.COMMAND_FEED)) {
				if(args.length == 0) {
					p.setFoodLevel(20);
					p.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("sucessFeed")));
				} else if(args.length == 1) {
					if(p.hasPermission(Permissions.COMMAND_FEED_OTHERS)) {
						Player target = Bukkit.getPlayerExact(args[0]);
						if(target == null) {
							p.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("targetNotOnlineFeed")));
						} else {
							target.setFoodLevel(20);
							p.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("targetFeed")
									.replace("&", "§")
									.replace("%target%", target.getName())));
							target.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("targetFeed2")
									.replace("&", "§")
									.replace("%player%", p.getName())));
						}
					} else {
						p.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_FEED_OTHERS));
					}
				} else if(args.length >= 2) {
					p.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("incorrectUsageFeed")));
				}
			} else {
				p.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_FEED));
			}
		}
		return false;
	}
}