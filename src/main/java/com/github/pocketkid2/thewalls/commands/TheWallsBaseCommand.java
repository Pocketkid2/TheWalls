package com.github.pocketkid2.thewalls.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.pocketkid2.thewalls.TheWallsPlugin;

public class TheWallsBaseCommand implements CommandExecutor {

	private TheWallsPlugin plugin;

	private List<TheWallsSubCommand> subCommands;

	public TheWallsBaseCommand(TheWallsPlugin p) {
		plugin = p;
		subCommands = new ArrayList<TheWallsSubCommand>();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		plugin.log(String.format("Received command %s with args %s", label, String.join(", ", args)));
//		for (TheWallsSubCommand sub : subCommands) {
//			for (String name : sub.names()) {
//				if (name.equalsIgnoreCase(args[0])) {
//
//				}
//			}
//		}
		return true;
	}

}
