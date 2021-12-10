package com.github.pocketkid2.thewalls;

import org.bukkit.plugin.java.JavaPlugin;

public class TheWallsPlugin extends JavaPlugin {

	private boolean debug;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		getConfig().getBoolean("debug", false);
		log("Enabled!");
	}

	@Override
	public void onDisable() {
		log("Disabled!");
	}

	public void log(String message) {
		getLogger().info(message);
	}

	public void warn(String message) {
		getLogger().warning(message);
	}

	public void error(String message) {
		getLogger().severe(message);
	}

	public void debug(String message) {
		if (debug) {
			getLogger().info("[DEBUG] " + message);
		}
	}
}
