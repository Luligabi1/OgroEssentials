package me.luligabi.ogroessentials.commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.luligabi.ogroessentials.OgroEssentials;
import me.luligabi.ogroessentials.utils.MessageUtils;
import me.luligabi.ogroessentials.utils.Permissions;

public class SmeltCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration cfg = OgroEssentials.plugin.getConfig(); 
    	String prefix = cfg.getString("prefixSmelt");
    	
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission(Permissions.COMMAND_SMELT)) {
				ItemStack hand = p.getInventory().getItemInMainHand();
				
				ArrayList<Material> ores = new ArrayList<Material>();
				ores.add(Material.IRON_ORE);
				ores.add(Material.GOLD_ORE);
				ores.add(Material.NETHER_GOLD_ORE);
				
				if(hand.getType() == Material.IRON_ORE) {
					hand.setType(Material.IRON_INGOT);
					p.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("sucessSmelt")));
				} else if(hand.getType() == Material.GOLD_ORE || hand.getType() == Material.NETHER_GOLD_ORE) {
					hand.setType(Material.GOLD_INGOT);
					p.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("sucessSmelt")));
				} else if(!ores.contains(hand.getType()) && hand.getType() != Material.AIR){
					p.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("invalidItemSmelt")));
				} else {
					p.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("noItemSmelt")));
				}
			}
		} else {
			sender.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_SMELT));
		}
		return true;
	}

}