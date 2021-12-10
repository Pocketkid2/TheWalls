package com.github.pocketkid2.thewalls;

import java.util.List;

public class GameManager {

	private TheWallsPlugin plugin;

	private List<Arena> arenas;

	public GameManager(TheWallsPlugin p) {
		plugin = p;
	}

	public List<Arena> getArenas() {
		return arenas;
	}
}
