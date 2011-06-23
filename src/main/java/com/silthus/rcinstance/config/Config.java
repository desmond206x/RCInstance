package com.silthus.rcinstance.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.bukkit.util.config.Configuration;

import com.silthus.rcinstance.RCInstance;
import com.silthus.rcinstance.util.RCLogger;

public class Config {
	
	private static RCInstance plugin;
	
	protected static String configFileName = "config.yml";
	private static Configuration config = null;
	private static File configFile;
	
	public static String mainWorld = null;
	public static List<String> instanceList = null;
	public static HashMap<Integer, String> instances;

	public static void initialize(RCInstance instance) {
		Config.plugin = instance;
		load();
	}

	public static void load() {
		
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdirs();
		}

		configFile = new File(plugin.getDataFolder().getAbsolutePath()
				+ File.separator + configFileName);

		if (!configFile.exists()) {
			createNew(configFile);
			RCLogger.warning("No config found! Creating new one...");
		}
		config = new Configuration(configFile);
		config.load();
		if (config.getInt("configversion", 1) < 1) {
			File renameFile = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + configFileName + "_old");
			if (renameFile.exists())
				renameFile.delete();
			configFile.renameTo(renameFile);
			createNew(configFile);
			RCLogger.warning("Old config found! Renaming and creating new one...");
			config = new Configuration(configFile);
			config.load();
		}
		Config.setup(config);
	}

	private static boolean createNew(File configFile) {

		try {
			InputStream stream = RCInstance.class.getResourceAsStream("/"
					+ configFileName);
			OutputStream out = new FileOutputStream(configFile);

			byte[] buf = new byte[1024];
			int len;
			while ((len = stream.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			stream.close();
			out.close();
			return true;
		} catch (IOException iex) {
			RCLogger.warning("Could not create config file! Aborting...");
			return false;
		}
	}

	private static void setup(Configuration file) {
		mainWorld = file.getString("mainWorld", "world");
		instanceList = file.getKeys("instances");
		if (instanceList != null) {
			int i = 0;
			for (String s : instanceList) {
				instances.put(i, s);
				i++;
			}
		}
		
	}
	
	public static InstanceConfig getInstance(int id) {
		if (instanceList != null && instances.containsKey(id)) {
			String name = instances.get(id);
			return new InstanceConfig(id, name, config);
		}
		return null;
	}
}
