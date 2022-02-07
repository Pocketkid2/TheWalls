package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.TheWallsPlugin;
import com.github.pocketkid2.thewalls.Utils;

import net.md_5.bungee.api.ChatColor;

public class ArenaInfoCommand extends TheWallsSubCommand {

	public ArenaInfoCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return false;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("arenainfo", "info");
	}

	@Override
	public String description() {
		return "Shows you admin information about the arena";
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
		return "arenainfo/info <name>";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Arena arena = plugin.getGM().getArenaByName(args[0]);
		if (arena == null) {
			sender.sendMessage(plugin.addPrefix(ChatColor.RED + "The arena " + ChatColor.GRAY + args[0] + ChatColor.RED + " does not exist!"));
			return;
		}
		sender.sendMessage(plugin.addPrefix(ChatColor.AQUA + "Showing information for arena " + ChatColor.GREEN + arena.getName()));
		sender.sendMessage(plugin.addPrefix(ChatColor.AQUA + "Status: " + ChatColor.BLUE + arena.getStatus().toString()));
		sender.sendMessage(plugin.addPrefix(ChatColor.AQUA + "Join Sign: " + (arena.getJoinSign() == null ? ChatColor.RED + "null" : ChatColor.GOLD + Utils.printLoc(arena.getJoinSign()))));
		sender.sendMessage(plugin.addPrefix(ChatColor.AQUA + "Player Sign: " + (arena.getPlayerSign() == null ? ChatColor.RED + "null" : ChatColor.GOLD + Utils.printLoc(arena.getPlayerSign()))));

	}

}
