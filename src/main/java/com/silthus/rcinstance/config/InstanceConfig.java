package com.silthus.rcinstance.config;

import org.bukkit.util.config.Configuration;

public class InstanceConfig {
	
	private String instanceName = null;
	private String world = null;
	private int maxPlayers = 0;
	private int timer = 0;
	private int exp = 0;

	public InstanceConfig(int id, String name, Configuration config) {
		config.load();
		this.instanceName = name;
		this.world = config.getString("instances." + this.instanceName + ".world", null);
		this.maxPlayers = config.getInt("instances." + this.instanceName + ".players", 0);
		this.timer = config.getInt("instances." + this.instanceName + ".timer", 0);
		this.exp = config.getInt("instances." + this.instanceName + ".exp", 0);
	}
	
	public String getName() {
		return this.instanceName;
	}
	
	public String getWorld() {
		return this.world;
	}
	
	public int getMaxPlayers() {
		return this.maxPlayers;
	}
	
	public int getTimer() {
		return this.timer;
	}
	
	public int getExp() {
		return this.exp;
	}

}
