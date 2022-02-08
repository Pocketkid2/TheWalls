package com.github.pocketkid2.thewalls.tasks;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.Status;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

public class StartGameTask extends BukkitRunnable {

	private TheWallsPlugin plugin;
	private int secondsLeft;
	private Arena arena;

	public StartGameTask(TheWallsPlugin p, int seconds, Arena a) {
		plugin = p;
		secondsLeft = seconds;
		arena = a;
	}

	@Override
	public void run() {
		secondsLeft--;
		plugin.debug("Running start game task, secondsLeft = " + secondsLeft);
		if (secondsLeft > 0) {
			arena.broadcast(ChatColor.AQUA + "The game will start in " + ChatColor.BLUE + secondsLeft + ChatColor.AQUA + " seconds");
		} else {
			arena.saveState();
			arena.setStatus(Status.INGAME);
			arena.broadcast(ChatColor.AQUA + "The game has started! You have 15 minutes before the walls disappear!");
			new DropWallsTask(plugin, 15, arena).runTaskTimer(plugin, 0, 20 * 60);
			cancel();
		}
	}

}
