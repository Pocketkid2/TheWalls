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

public class Arena implements ConfigurationSerializable {

	static {
		ConfigurationSerialization.registerClass(Arena.class);
	}

	private String name;
	private Status status;

	private Location joinSign;
	private Location playerSign;

	private Region arena;

	private List<Region> walls;

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

		walls = new ArrayList<Region>();

		spawns = new ArrayList<Location>();

		activePlayers = new ArrayList<Player>();
		inactivePlayers = new ArrayList<OfflinePlayer>();
	}

	//
	// DESERIALIZER
	//
	public Arena(Map<String, Object> map) {
		name = (String) map.get("name");

		joinSign = (Location) map.get("join-sign");
		playerSign = (Location) map.get("player-sign");

		arena = (Region) map.get("arena-region");

		walls = (List<Region>) map.get("wall-regions");
		if (walls == null) {
			walls = new ArrayList<Region>();
		}

		spawns = (List<Location>) map.get("spawn-locations");
		if (spawns == null) {
			spawns = new ArrayList<Location>();
		}

		activePlayers = new ArrayList<Player>();
		inactivePlayers = new ArrayList<OfflinePlayer>();

		if (joinSign != null && playerSign != null && arena != null && walls.size() > 0 && spawns.size() == 4) {
			status = Status.READY;
		} else {
			status = Status.INCOMPLETE;
		}
	}

	//
	// SERIALIZER
	//
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("name", name);

		map.put("join-sign", joinSign);
		map.put("player-sign", playerSign);

		map.put("arena-region", arena);

		map.put("wall-regions", walls);

		map.put("spawn-locations", spawns);

		return map;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status newStatus) {
		status = newStatus;
	}

	public Location getJoinSign() {
		return joinSign;
	}

	public void setJoinSign(Location newLoc) {
		joinSign = newLoc;
	}

	public Location getPlayerSign() {
		return playerSign;
	}

	public void setPlayerSign(Location newLoc) {
		playerSign = newLoc;
	}

	public Region getArenaRegion() {
		return arena;
	}

	public void setArenaRegion(Region newRegion) {
		arena = newRegion;
	}

	public List<Region> getWallRegions() {
		return walls;
	}

	public void addWallRegion(Region newRegion) {
		walls.add(newRegion);
	}

	public void clearWallRegions() {
		walls.clear();
	}

	public List<Location> getSpawnLocations() {
		return spawns;
	}

	public void addSpawnLocation(Location newLoc) {
		spawns.add(newLoc);
	}

	public void clearSpawnLocations() {
		spawns.clear();
	}

	public List<Player> getActivePlayers() {
		return activePlayers;
	}

}
