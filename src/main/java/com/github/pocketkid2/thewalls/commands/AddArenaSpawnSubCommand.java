package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.Status;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

public class AddArenaSpawnSubCommand extends TheWallsSubCommand {

	public AddArenaSpawnSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return true;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("addarenaspawn", "addspawn", "as");
	}

	@Override
	public String description() {
		return "Adds your current location as a spawn location for the specified arena";
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
		return "addspawn/as <name>";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Player player = (Player) sender;
		Arena arena = plugin.getGM().getArenaByName(args[0]);
		if (arena == null) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "The arena " + ChatColor.GRAY + args[0] + ChatColor.RED + " does not exist!"));
			return;
		}
		if ((arena.getStatus() != Status.INCOMPLETE) && (arena.getStatus() != Status.READY)) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "Please wait till the game is finished to modify it's values"));
			return;
		}
		arena.addSpawnLocation(player.getLocation());
		player.sendMessage(plugin.addPrefix(ChatColor.AQUA + "Your location has been added to the list of spawns for " + ChatColor.GRAY + arena.getName()));
	}

}
