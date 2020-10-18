package me.luligabi.ogroessentials;

import org.bukkit.plugin.java.JavaPlugin;

import me.luligabi.ogroessentials.commands.ClearChatCommand;
import me.luligabi.ogroessentials.commands.ClockCommand;
import me.luligabi.ogroessentials.commands.DayCommand;
import me.luligabi.ogroessentials.commands.FeedCommand;
import me.luligabi.ogroessentials.commands.GameModeCommand;
import me.luligabi.ogroessentials.commands.HealCommand;
import me.luligabi.ogroessentials.commands.NightCommand;
import me.luligabi.ogroessentials.commands.OgroEssentialsCommand;
import me.luligabi.ogroessentials.commands.RainCommand;
import me.luligabi.ogroessentials.commands.SmeltCommand;
import me.luligabi.ogroessentials.commands.SunCommand;
import me.luligabi.ogroessentials.commands.ThunderCommand;
import me.luligabi.ogroessentials.commands.TpAllCommand;
import me.luligabi.ogroessentials.commands.TpHereCommand;

public class OgroEssentials extends JavaPlugin {
	
	public static OgroEssentials plugin;
		
	@Override
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		registerCommands();

	}
	@Override
	public void onDisable() {
		
	}
	public void registerCommands() {
		this.getCommand("ogroessentials").setExecutor(new OgroEssentialsCommand());
		this.getCommand("gamemode").setExecutor(new GameModeCommand());
		this.getCommand("heal").setExecutor(new HealCommand());
		this.getCommand("feed").setExecutor(new FeedCommand());
		this.getCommand("tphere").setExecutor(new TpHereCommand());
		this.getCommand("tpall").setExecutor(new TpAllCommand());
		this.getCommand("clearchat").setExecutor(new ClearChatCommand());
		this.getCommand("clock").setExecutor(new ClockCommand());
		this.getCommand("day").setExecutor(new DayCommand());
		this.getCommand("night").setExecutor(new NightCommand());
		this.getCommand("sun").setExecutor(new SunCommand());
		this.getCommand("rain").setExecutor(new RainCommand());
		this.getCommand("thunder").setExecutor(new ThunderCommand());
		this.getCommand("smelt").setExecutor(new SmeltCommand());
	}
}