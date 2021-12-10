package com.github.pocketkid2.thewalls.commands;

import java.util.List;

import com.github.pocketkid2.thewalls.TheWallsPlugin;

public abstract class TheWallsSubCommand {

	private TheWallsPlugin plugin;

	public TheWallsSubCommand(TheWallsPlugin p) {
		plugin = p;
	}

	public abstract boolean mustBePlayer();

	public abstract List<String> names();

	public abstract int minArguments();

	public abstract int maxArguments();

	public abstract boolean isAdminCommand();

	// Because we supply a getter for the argument range,
	// we guarantee that the array will be within that range
	public abstract void execute(String[] args);
}
