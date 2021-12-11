package com.github.pocketkid2.thewalls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.regions.CuboidRegion;

public class Arena implements ConfigurationSerializable {

	private String name;
	private Status status;

	private CuboidRegion arena;
	private List<CuboidRegion> walls;
	private List<Location> spawns;

	private List<Player> activePlayers;
	private List<OfflinePlayer> inactivePlayers;

	public Arena(String n) {
		name = n;
		status = Status.INCOMPLETE;
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
		status = Status.INCOMPLETE;
		arena = null;
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
