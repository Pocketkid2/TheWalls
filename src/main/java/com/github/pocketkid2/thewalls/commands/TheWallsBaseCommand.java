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
		subCommands.add(new LobbySubCommand(p));
		subCommands.add(new SetLobbySpawnSubCommand(p));
		subCommands.add(new SetArenaJoinSignSubCommand(p));
		subCommands.add(new SetArenaPlayerSignSubCommand(p));
		subCommands.add(new SetArenaRegionSubCommand(p));
		subCommands.add(new AddArenaWallSubCommand(p));
		subCommands.add(new ClearRegionSubCommand(p));
		subCommands.add(new ClearWallsSubCommand(p));
		subCommands.add(new TeleportSubCommand(p));
		subCommands.add(new ArenaInfoCommand(p));

		subCommands.add(new HelpSubCommand(p));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length <= 0) {
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
					if (sub.mustBePlayer() && !(sender instanceof Player)) {
						sender.sendMessage(plugin.addPrefix(ChatColor.RED + "That command can only be executed by players!"));
						return true;
					}
					if (sub.isAdminCommand() && !sender.hasPermission("thewalls.admin")) {
						sender.sendMessage(plugin.addPrefix(ChatColor.RED + "That command can only be executed by admins with the right permission!"));
						return true;
					}
					if (!sub.isAdminCommand() && !sender.hasPermission("thewalls.player")) {
						sender.sendMessage(plugin.addPrefix(ChatColor.RED + "That command can only be executed by players with the right permission!"));
						return true;
					}
					if ((args.length - 1) < sub.minArguments()) {
						sender.sendMessage(plugin.addPrefix(ChatColor.RED + "That command requires more arguments!"));
						sender.sendMessage(plugin.addPrefix(ChatColor.RED + "Usage: " + ChatColor.GRAY + "/" + label + " " + sub.usageMessage()));
						return true;
					}
					if ((args.length - 1) > sub.maxArguments()) {
						sender.sendMessage(plugin.addPrefix(ChatColor.RED + "That command doesn't require that many arguments!"));
						sender.sendMessage(plugin.addPrefix(ChatColor.RED + "Usage: " + ChatColor.GRAY + "/" + label + " " + sub.usageMessage()));
						return true;
					}
					sub.execute(sender, label, Arrays.copyOfRange(args, 1, args.length));
					return true;
				}
			}
		}

		sender.sendMessage(plugin.addPrefix(ChatColor.RED + "The command you entered, " + ChatColor.GRAY + "/" + label + " " + args[0] + ChatColor.RED + ", isn't a valid command."));
		sender.sendMessage(plugin.addPrefix(ChatColor.RED + "Try " + ChatColor.GRAY + "/" + label + " help" + ChatColor.RED + " to see a list of valid commands"));
		return true;
	}

	public class HelpSubCommand extends TheWallsSubCommand {

		public HelpSubCommand(TheWallsPlugin p) {
			super(p);
		}

		@Override
		public boolean mustBePlayer() {
			return false;
		}

		@Override
		public List<String> names() {
			return Arrays.asList("help");
		}

		@Override
		public String description() {
			return "Shows a list of commands or help for a specific command";
		}

		@Override
		public int minArguments() {
			return 0;
		}

		@Override
		public int maxArguments() {
			return 1;
		}

		@Override
		public boolean isAdminCommand() {
			return false;
		}

		@Override
		public String usageMessage() {
			return "help <command>";
		}

		@Override
		public void execute(CommandSender sender, String label, String[] args) {
			if (args.length > 0) {
				for (TheWallsSubCommand sub : subCommands) {
					for (String name : sub.names()) {
						if (name.equalsIgnoreCase(args[0])) {
							sender.sendMessage(plugin.addPrefix(ChatColor.AQUA + "That command is used as follows: " + ChatColor.GOLD + "/" + label + " " + sub.usageMessage()));
						}
					}
				}
			} else {
				sender.sendMessage(plugin.addPrefix(ChatColor.AQUA + "The following commands are available:"));
				for (TheWallsSubCommand sub : subCommands) {
					if (sub.isAdminCommand() && sender.hasPermission("thewalls.admin")) {
						sender.sendMessage(plugin.addPrefix(ChatColor.GOLD + "/" + label + " " + sub.usageMessage() + ChatColor.GRAY + " - " + sub.description()));
					}
				}
			}
		}

	}

}
