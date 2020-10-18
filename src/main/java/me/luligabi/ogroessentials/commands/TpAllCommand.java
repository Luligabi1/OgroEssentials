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

public class TpAllCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration cfg = OgroEssentials.plugin.getConfig();
		String prefix = cfg.getString("prefixTpAll");
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission(Permissions.COMMAND_TPALL)) {
				for(Player players: Bukkit.getServer().getOnlinePlayers()) {
					players.teleport(p.getLocation());
					players.sendMessage(MessageUtils.infoMessage(prefix, cfg.getString("sucessTpAll2")
							.replace("&", "§")
							.replace("%player%", p.getName())));
				}
				p.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("sucessTpAll")));
			} else {
				p.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_TPALL));
			}
		}
		return true;
	}
}