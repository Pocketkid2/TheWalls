package com.github.pocketkid2.thewalls;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.pocketkid2.thewalls.commands.TheWallsBaseCommand;

public class TheWallsPlugin extends JavaPlugin {

	private boolean debug;

	private File dataFile;
	private FileConfiguration dataConfig;

	private Location lobbySpawn;

	private GameManager gm;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		createDataConfig();

		debug = getConfig().getBoolean("debug", false);
		debug("Debug messages turned on!");

		getCommand("thewalls").setExecutor(new TheWallsBaseCommand(this));

		lobbySpawn = getConfig().getSerializable("lobby-spawn-location", Location.class);
		debug("Lobby spawn loaded as " + (lobbySpawn == null ? "null" : lobbySpawn.toString()));

		gm = new GameManager(this);

		log("Enabled!");
	}

	@Override
	public void onDisable() {
		dataConfig.set("lobby-spawn-location", lobbySpawn);
		dataConfig.set("arena-data", gm.getArenaData());

		saveConfig();
		try {
			dataConfig.save(dataFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		log("Disabled!");
	}

	private void createDataConfig() {
		dataFile = new File(getDataFolder(), "data.yml");
		if (!dataFile.exists()) {
			dataFile.getParentFile().mkdirs();
			saveResource("data.yml", false);
		}

		dataConfig = new YamlConfiguration();
		try {
			dataConfig.load(dataFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
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

	public Location getLobbySpawn() {
		return lobbySpawn;
	}

	public void setLobbySpawn(Location loc) {
		lobbySpawn = loc;
	}

	public GameManager getGM() {
		return gm;
	}
}
