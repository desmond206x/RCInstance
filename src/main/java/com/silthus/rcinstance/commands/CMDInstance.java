package com.silthus.rcinstance.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.silthus.rcinstance.RCInstance;

public class CMDInstance implements CommandExecutor {
	
	private final RCInstance plugin;
	private boolean handled = false;
	
	public CMDInstance (RCInstance instance) {
		this.plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		return this.handled;
	}

}
