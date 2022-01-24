package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

import net.md_5.bungee.api.ChatColor;

public class DeleteSubCommand extends TheWallsSubCommand {

	public DeleteSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return false;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("delete", "remove");
	}

	@Override
	public String description() {
		return "Deletes the specified arena";
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
		return "delete <name>";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Arena arena = plugin.getGM().getArenaByName(args[0]);

		if (arena == null) {
			sender.sendMessage(plugin.addPrefix(ChatColor.RED + "The arena " + ChatColor.GRAY + args[0] + ChatColor.RED + " doesn't exist!"));
		} else {
			plugin.getGM().removeArena(args[0]);
			sender.sendMessage(plugin.addPrefix(ChatColor.AQUA + "Deleted arena " + ChatColor.GREEN + arena.getName()));
		}
	}

}
