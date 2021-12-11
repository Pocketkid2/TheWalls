package com.github.pocketkid2.thewalls;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.regions.CuboidRegion;

public class Arena {

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
