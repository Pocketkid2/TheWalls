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
	public void execute(CommandSender sender, String[] args) {
		for (Arena a : plugin.getGM().getArenas()) {
			if (a.getName().equalsIgnoreCase(args[0])) {
				plugin.getGM().removeArena(args[0]);
				sender.sendMessage(ChatColor.AQUA + "Deleted arena " + ChatColor.GREEN + a.getName());
				return;
			}
		}
		sender.sendMessage(ChatColor.RED + "The arena " + ChatColor.GRAY + args[0] + ChatColor.RED + " doesn't exist!");
	}

}
