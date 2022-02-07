package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

public class TeleportSubCommand extends TheWallsSubCommand {

	public TeleportSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return true;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("teleport", "tp");
	}

	@Override
	public String description() {
		return "Teleports you to the first spawn (or a specified spawn) in the specified arena";
	}

	@Override
	public int minArguments() {
		return 1;
	}

	@Override
	public int maxArguments() {
		return 2;
	}

	@Override
	public boolean isAdminCommand() {
		return true;
	}

	@Override
	public String usageMessage() {
		return "teleport/tp <name> [id]";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Player player = (Player) sender;
		Arena a = plugin.getGM().getArenaByName(args[0]);
		if (a == null) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "The arena " + ChatColor.GRAY + args[0] + ChatColor.RED + " does not exist!"));
			return;
		}
		List<Location> spawns = a.getSpawnLocations();
		if (spawns == null || spawns.size() < 1) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "The arena " + ChatColor.GRAY + a.getName() + ChatColor.RED + " has no spawn locations defined!"));
			return;
		}
		Location loc;
		if (args.length > 1) {
			try {
				loc = spawns.get(Integer.parseInt(args[1]) - 1);
			} catch (NumberFormatException e) {
				player.sendMessage(plugin.addPrefix(ChatColor.RED + "The index " + ChatColor.GRAY + "'" + args[1] + "'" + ChatColor.RED + " that you provided is not a valid number!"));
				return;
			} catch (IndexOutOfBoundsException e) {
				player.sendMessage(plugin.addPrefix(ChatColor.RED + "Arena " + ChatColor.GRAY + a.getName() + ChatColor.RED + " does not have a spawn location of index " + ChatColor.GRAY + args[1]));
				player.sendMessage(plugin.addPrefix(ChatColor.RED + "Valid indexes: " + ChatColor.GRAY
						+ String.join(", ", IntStream.rangeClosed(1, a.getSpawnLocations().size()).boxed().map(s -> s.toString()).collect(Collectors.toList()))));
				return;
			}
		} else {
			loc = spawns.get(0);
		}
		player.teleport(loc);
		player.sendMessage(plugin.addPrefix(ChatColor.AQUA + "You have been teleported to a spawn location for arena " + ChatColor.GREEN));
	}

}
