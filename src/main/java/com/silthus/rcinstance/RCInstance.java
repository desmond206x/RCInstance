package com.silthus.rcinstance;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.silthus.rcinstance.commands.CMDInstance;
import com.silthus.rcinstance.config.Config;
import com.silthus.rcinstance.listeners.PluginListener;
import com.silthus.rcinstance.util.CommandManager;
import com.silthus.rcinstance.util.RCLogger;

public class RCInstance extends JavaPlugin {

	private final CommandManager commandManager = new CommandManager(this);
	private final PluginListener pluginListener = new PluginListener(this);

	public static String name;
	public static String version;

	public void onEnable() {
		name = this.getDescription().getName();
		version = this.getDescription().getVersion();
		PluginManager pm = getServer().getPluginManager();

		// Logger
		RCLogger.initialize(Logger.getLogger("Minecraft"));
		// Config
		Config.initialize(this);

		RCLogger.info(name + " version " + version + " is enabled!");
	}

	/*
	 * Sets up the core commands of the plugin.
	 */
	private void setupCommands() {
		// Add command labels here.
		addCommand("rci", new CMDInstance(this));
	}

	/*
	 * Executes a command when a command event is received.
	 * 
	 * @param sender The thing that sent the command.
	 * 
	 * @param cmd The complete command object.
	 * 
	 * @param label The label of the command.
	 * 
	 * @param args The arguments of the command.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		return commandManager.dispatch(sender, cmd, label, args);
	}

	/*
	 * Adds the specified command to the command manager and server.
	 * 
	 * @param command The label of the command.
	 * 
	 * @param executor The command class that excecutes the command.
	 */
	private void addCommand(String command, CommandExecutor executor) {
		getCommand(command).setExecutor(executor);
		commandManager.addCommand(command, executor);
	}

	public CommandManager getCommandManager() {
		return this.commandManager;
	}

	public void onDisable() {

		RCLogger.info(name + " disabled.");
	}

}
