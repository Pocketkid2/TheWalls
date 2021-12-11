package com.github.pocketkid2.thewalls;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.pocketkid2.thewalls.commands.TheWallsBaseCommand;

public class TheWallsPlugin extends JavaPlugin {

	private boolean debug;

	private GameManager gm;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		debug = getConfig().getBoolean("debug", false);
		getCommand("thewalls").setExecutor(new TheWallsBaseCommand(this));
		gm = new GameManager(this);
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

	public GameManager getGM() {
		return gm;
	}
}
