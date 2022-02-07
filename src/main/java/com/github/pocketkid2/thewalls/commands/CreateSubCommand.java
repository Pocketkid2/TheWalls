package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

public class CreateSubCommand extends TheWallsSubCommand {

	public CreateSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return false;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("create");
	}

	@Override
	public String description() {
		return "Creates a new arena with the specified name";
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
		return "create <name>";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		for (Arena a : plugin.getGM().getArenas()) {
			if (a.getName().equalsIgnoreCase(args[0])) {
				sender.sendMessage(plugin.addPrefix(ChatColor.RED + "The arena " + ChatColor.GRAY + a.getName() + ChatColor.RED + " already exists!"));
				return;
			}
		}
		plugin.getGM().createArena(args[0]);
		sender.sendMessage(plugin.addPrefix(ChatColor.AQUA + "Created arena " + ChatColor.GREEN + args[0]));
	}

}
