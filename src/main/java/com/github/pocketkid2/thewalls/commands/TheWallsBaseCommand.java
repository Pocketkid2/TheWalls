package com.github.pocketkid2.thewalls.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import com.github.pocketkid2.thewalls.Status;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

public class TheWallsBaseCommand implements CommandExecutor {

	private TheWallsPlugin plugin;

	private List<TheWallsSubCommand> subCommands;

	public TheWallsBaseCommand(TheWallsPlugin p) {
		plugin = p;
		subCommands = new ArrayList<TheWallsSubCommand>();
		subCommands.add(new CreateSubCommand(p));
		subCommands.add(new ListSubCommand(p));
		subCommands.add(new DeleteSubCommand(p));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length <= 0) {
			// If the command is run empty, show basic information and stats about the
			// plugin
			PluginDescriptionFile pdf = plugin.getDescription();
			sender.sendMessage(ChatColor.AQUA + "This server is running " + ChatColor.GOLD + pdf.getName() + ChatColor.AQUA + " version " + ChatColor.GOLD + pdf.getVersion());
			sender.sendMessage(ChatColor.AQUA + "There are " + ChatColor.YELLOW + plugin.getGM().getArenas().size() + ChatColor.AQUA + " arenas, with " + ChatColor.YELLOW
					+ plugin.getGM().getArenas().stream().filter(a -> a.getStatus() == Status.INGAME).collect(Collectors.toList()).size() + ChatColor.AQUA + " currently in-game");
			sender.sendMessage(ChatColor.AQUA + "Use " + ChatColor.DARK_GREEN + "/" + label + " help" + ChatColor.AQUA + " to see a list of commands");
			return true;
		}

		for (TheWallsSubCommand sub : subCommands) {
			for (String name : sub.names()) {
				if (name.equalsIgnoreCase(args[0])) {
					// If we found a matching subcommand, do the checks and execute
					if (sub.mustBePlayer() && !(sender instanceof Player)) {
						sender.sendMessage(ChatColor.RED + "That command can only be executed by players!");
						return true;
					}

					if (sub.isAdminCommand() && !sender.hasPermission("thewalls.admin")) {
						sender.sendMessage(ChatColor.RED + "That command can only be executed by admins with the right permission!");
						return true;
					}
					if (!sub.isAdminCommand() && !sender.hasPermission("thewalls.player")) {
						sender.sendMessage(ChatColor.RED + "That command can only be executed by players with the right permission!");
						return true;
					}

					if (args.length - 1 < sub.minArguments()) {
						sender.sendMessage(ChatColor.RED + "That command requires more arguments!");
						sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GRAY + "/" + label + " " + sub.usageMessage());
						return true;
					}
					if (args.length - 1 > sub.maxArguments()) {
						sender.sendMessage(ChatColor.RED + "That command doesn't require that many arguments!");
						sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GRAY + "/" + label + " " + sub.usageMessage());
						return true;
					}

					sub.execute(sender, Arrays.copyOfRange(args, 1, args.length));

					return true;
				}
			}
		}

		// If we got down here, we didn't find anything
		sender.sendMessage(ChatColor.RED + "The command you entered, " + ChatColor.GRAY + "/" + label + " " + args[0] + ChatColor.RED + ", isn't a valid command.");
		sender.sendMessage(ChatColor.RED + "Try " + ChatColor.GRAY + "/" + label + " help" + ChatColor.RED + " to see a list of valid commands");
		return true;
	}

	private void help(CommandSender sender) {

	}

}
