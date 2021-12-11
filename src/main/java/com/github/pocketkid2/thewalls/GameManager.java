package com.github.pocketkid2.thewalls;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameManager {

	private TheWallsPlugin plugin;

	private List<Arena> arenas;

	public GameManager(TheWallsPlugin p) {
		plugin = p;
		arenas = new ArrayList<Arena>();
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
		arenas.add(new Arena(name));
	}

	public void removeArena(String name) {
		arenas = arenas.stream().filter(a -> !a.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
	}

	public List<Arena> getArenaData() {
		return arenas;
	}
}
