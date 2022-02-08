package com.github.pocketkid2.thewalls;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GameManager {

	private TheWallsPlugin plugin;

	private List<Arena> arenas;

	@SuppressWarnings("unchecked")
	public GameManager(TheWallsPlugin p) {
		plugin = p;
		arenas = (List<Arena>) plugin.getDataConfig().getList("arena-data", new ArrayList<Arena>());
		plugin.log("Loaded " + arenas.size() + " arenas!");
	}

	public List<Arena> getArenas() {
		return arenas;
	}

	// If the specified arena does not exist, the method returns null
	public Arena getArenaByName(String name) {
		for (Arena a : arenas) {
			if (a.getName().equalsIgnoreCase(name))
				return a;
		}
		return null;
	}

	public void createArena(String name) {
		arenas.add(new Arena(plugin, name));
	}

	public void removeArena(String name) {
		arenas = arenas.stream().filter(a -> !a.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
	}

	public Arena getArenaForPlayer(Player player) {
		for (Arena arena : arenas) {
			if (arena.isPlayer(player))
				return arena;
		}
		return null;
	}

	public boolean isInGame(Player player) {
		for (Arena arena : arenas) {
			if (arena.isPlayer(player))
				return true;
		}
		return false;
	}

	// Force shuts down all games peacefully
	public void shutdown() {
		for (Arena arena : arenas) {
			arena.endGame();
		}
	}

	public boolean isProtected(Block block) {
		for (Arena arena : arenas) {
			if (arena.isProtected(block))
				return true;
		}
		return false;
	}
}
