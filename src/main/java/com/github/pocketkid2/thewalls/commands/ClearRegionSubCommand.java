package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

public class ClearRegionSubCommand extends TheWallsSubCommand {

	public ClearRegionSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return false;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("clearregion", "cr");
	}

	@Override
	public String description() {
		return "Clears arena region (and by definition wall regions) for the specified arena";
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
		return "clearregion/cr <name>";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Arena arena = plugin.getGM().getArenaByName(args[0]);
		if (arena == null) {
			sender.sendMessage(plugin.addPrefix(ChatColor.RED + "The arena " + ChatColor.GRAY + args[0] + ChatColor.RED + " does not exist!"));
			return;
		}
		arena.clearWallRegions();
		arena.setArenaRegion(null);
		sender.sendMessage(plugin.addPrefix(ChatColor.AQUA + "Cleared arena region and wall regions for arena " + ChatColor.GRAY + arena.getName()));
	}

}
