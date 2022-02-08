package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.Status;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

public class ClearSpawnsSubCommand extends TheWallsSubCommand {

	public ClearSpawnsSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return false;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("clearspawns", "cs");
	}

	@Override
	public String description() {
		return "Clears all spawns for the specified arena";
	}

	@Override
	public int minArguments() {
		return 1;
	}

	@Override
	public int maxArguments() {
		return 1;
	}

	@Override
	public boolean isAdminCommand() {
		return true;
	}

	@Override
	public String usageMessage() {
		return "clearspawns/cs <name>";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Arena arena = plugin.getGM().getArenaByName(args[0]);
		if (arena == null) {
			sender.sendMessage(plugin.addPrefix(ChatColor.RED + "The arena " + ChatColor.GRAY + args[0] + ChatColor.RED + " does not exist!"));
			return;
		}
		if ((arena.getStatus() != Status.INCOMPLETE) && (arena.getStatus() != Status.READY)) {
			sender.sendMessage(plugin.addPrefix(ChatColor.RED + "Please wait till the game is finished to modify it's values"));
			return;
		}
		arena.clearSpawnLocations();
		sender.sendMessage(plugin.addPrefix(ChatColor.AQUA + "All spawns have been cleared for arena " + ChatColor.GRAY + arena.getName()));
	}

}
