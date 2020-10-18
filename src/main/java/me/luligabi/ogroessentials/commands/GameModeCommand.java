package me.luligabi.ogroessentials.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;

/*
 * 	Código por:
 * 	Mateusão#0001
 * 	Valeu! <3
 */

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.luligabi.ogroessentials.OgroEssentials;
import me.luligabi.ogroessentials.utils.MessageUtils;
import me.luligabi.ogroessentials.utils.Permissions;


public class GameModeCommand implements CommandExecutor {

    private final HashMap<Integer, GameMode> gameModes = new HashMap<>();

    public GameModeCommand() {
        gameModes.put(0, GameMode.SURVIVAL);
        gameModes.put(1, GameMode.CREATIVE);
        gameModes.put(2, GameMode.ADVENTURE);
        gameModes.put(3, GameMode.SPECTATOR);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	FileConfiguration cfg = OgroEssentials.plugin.getConfig(); 
    	String prefix = cfg.getString("prefixGameMode");
    	
    	if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.COMMAND_GAMEMODE)) {
                if (args.length == 0) {
                    player.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("incorrectUsageGameMode")));
                } else if (args.length == 1) {
                    if (!giveGameMode(player, args[0])) {
                        player.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("invalidGameMode")));
                    }
                } else if (args.length == 2 && player.hasPermission(Permissions.COMMAND_GAMEMODE_OTHERS)) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target == null)  {
                        player.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("targetNotOnlineGameMode")));
                    } else if (giveGameMode(target, args[0])){
                        player.sendMessage(MessageUtils.sucessMessage(prefix, cfg.getString("targetChangedGamemode")
                        		.replace("&", "§")
                				.replace("%target%", target.getName())));
                        target.sendMessage(MessageUtils.infoMessage(prefix, cfg.getString("targetChangedGamemode2")
                        		.replace("&", "§")
                				.replace("%player%", player.getName())));
                    } else {
                        player.sendMessage(MessageUtils.errorMessage(prefix, cfg.getString("invalidGameMode")));
                    }
                } else {
                    player.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_GAMEMODE_OTHERS));
                }
            } else {
                player.sendMessage(MessageUtils.permissionMessage(Permissions.COMMAND_GAMEMODE));
            }
        }
        return false;
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean giveGameMode(Player player, String arg) {
        GameMode gm = null;
        if (isInteger(arg)) {
            gm = gameModes.get(Integer.parseInt(arg));
        } else {
            try {
                gm = GameMode.valueOf(arg.toUpperCase());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if (gm == null) {
            return false;
        }
        player.setGameMode(gm);
        player.sendMessage(MessageUtils.sucessMessage("Modo de Jogo", "Seu modo de jogo foi alterado para §e" + gm.name()));
        return true;
    }
}