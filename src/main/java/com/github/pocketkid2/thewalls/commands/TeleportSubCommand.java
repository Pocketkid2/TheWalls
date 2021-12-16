package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

import net.md_5.bungee.api.ChatColor;

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
		return "teleport/tp <name>";
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		Arena a = plugin.getGM().getArenaByName(args[0]);
		if (a == null) {
			player.sendMessage(ChatColor.RED + "The arena " + ChatColor.GRAY + args[0] + ChatColor.RED + " does not exist!");
			return;
		}
		List<Location> spawns = a.getSpawnLocations();
		if (spawns == null || spawns.size() < 1) {
			player.sendMessage(ChatColor.RED + "The arena " + ChatColor.GRAY + a.getName() + ChatColor.RED + " has no spawn locations defined!");
			return;
		}
		player.teleport(spawns.get(0));
		player.sendMessage(ChatColor.AQUA + "You have been teleported to the first spawn location for arena " + ChatColor.GREEN);
	}

}
