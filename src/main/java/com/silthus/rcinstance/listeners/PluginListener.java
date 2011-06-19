package com.silthus.rcinstance.listeners;

import org.bukkit.event.server.ServerListener;

import com.silthus.rcinstance.RCInstance;

public class PluginListener extends ServerListener {

	private final RCInstance plugin;

	public PluginListener(RCInstance instance) {
		this.plugin = instance;
	}

}
