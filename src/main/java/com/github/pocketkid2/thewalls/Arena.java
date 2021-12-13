package com.github.pocketkid2.thewalls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.regions.CuboidRegion;

public class Arena implements ConfigurationSerializable {

	static {
		ConfigurationSerialization.registerClass(Arena.class);
	}

	private String name;
	private Status status;

	private Location joinSign;
	private Location playerSign;

	private Location arenaMin;
	private Location arenaMax;
	private CuboidRegion arena;

	private List<Location> wallMins;
	private List<Location> wallMaxs;
	private List<CuboidRegion> walls;

	private List<Location> spawns;

	private List<Player> activePlayers;
	private List<OfflinePlayer> inactivePlayers;

	// Initial constructor (when nothing but a name is given)
	public Arena(String n) {
		name = n;
		status = Status.INCOMPLETE;

		joinSign = null;
		playerSign = null;

		arena = null;

		walls = new ArrayList<CuboidRegion>();

		spawns = new ArrayList<Location>();

		activePlayers = new ArrayList<Player>();
		inactivePlayers = new ArrayList<OfflinePlayer>();
	}

	//
	// DESERIALIZER
	//
	public Arena(Map<String, Object> map) {
		name = (String) map.get("name");
		// status = Status.INCOMPLETE;

		joinSign = (Location) map.get("join-sign");
		playerSign = (Location) map.get("player-sign");

		arenaMin = (Location) map.get("arena-min");
		arenaMax = (Location) map.get("arena-max");
		if (arenaMin != null && arenaMax != null && arenaMin.getWorld() == arenaMax.getWorld()) {

		}

		walls = new ArrayList<CuboidRegion>();
		spawns = new ArrayList<Location>();
		activePlayers = new ArrayList<Player>();
		inactivePlayers = new ArrayList<OfflinePlayer>();
	}

	//
	// SERIALIZER
	//
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return map;
	}

	public String getName() {
		return name;
	}

	public Status getStatus() {
		return status;
	}

	public List<Player> getActivePlayers() {
		return activePlayers;
	}

}
