package com.github.pocketkid2.thewalls.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.github.pocketkid2.thewalls.TheWallsPlugin;

public abstract class TheWallsSubCommand {

	protected TheWallsPlugin plugin;

	public TheWallsSubCommand(TheWallsPlugin p) {
		plugin = p;
	}

	public abstract boolean mustBePlayer();

	public abstract List<String> names();

	public abstract String description();

	// Minimum is with respect to this subcommand, so for example /tw create <name>
	// has min 1 and max 1
	public abstract int minArguments();

	public abstract int maxArguments();

	public abstract boolean isAdminCommand();

	public abstract String usageMessage();

	// Because we supply a getter for the argument range,
	// we guarantee that the array will be within that range
	public abstract void execute(CommandSender sender, String label, String[] args);

}
