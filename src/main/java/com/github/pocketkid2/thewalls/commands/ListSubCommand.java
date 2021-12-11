package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

import net.md_5.bungee.api.ChatColor;

public class ListSubCommand extends TheWallsSubCommand {

	public ListSubCommand(TheWallsPlugin p) {
		super(p);

	}

	@Override
	public boolean mustBePlayer() {
		return false;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("list");
	}

	@Override
	public int minArguments() {
		return 0;
	}

	@Override
	public int maxArguments() {
		return 0;
	}

	@Override
	public boolean isAdminCommand() {
		return false;
	}

	@Override
	public String usageMessage() {
		return "list";
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage(ChatColor.AQUA + "Loaded arenas: (" + ChatColor.YELLOW + plugin.getGM().getArenas().size() + ChatColor.AQUA + ")");
		for (Arena a : plugin.getGM().getArenas()) {
			sender.sendMessage(ChatColor.GREEN + a.getName() + ChatColor.AQUA + " - " + ChatColor.GOLD + a.getActivePlayers().size() + ChatColor.AQUA + " active players"
					+ (a.getActivePlayers().size() > 0 ? ChatColor.GRAY + " (" + String.join(", ", args) + ")" : ""));
		}
	}

}