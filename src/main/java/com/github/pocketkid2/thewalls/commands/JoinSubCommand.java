package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

import net.md_5.bungee.api.ChatColor;

public class JoinSubCommand extends TheWallsSubCommand {

	public JoinSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return true;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("join");
	}

	@Override
	public String description() {
		return "Puts you in the specified arena";
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
		return false;
	}

	@Override
	public String usageMessage() {
		return "join <arena>";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Player player = (Player) sender;

		if (plugin.getGM().isInGame(player)) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "You are already in a game!"));
		} else {
			Arena arena = plugin.getGM().getArenaByName(args[0]);
			if (arena == null) {
				player.sendMessage(plugin.addPrefix(ChatColor.RED + "The arena " + ChatColor.GRAY + args[0] + ChatColor.RED + " doesn't exist!"));
			} else {
				switch (arena.getStatus()) {
				case INCOMPLETE:
					player.sendMessage(plugin.addPrefix(ChatColor.RED + "That arena has not been fully set up yet, please talk to an admin about it!"));
					break;
				case INGAME:
					player.sendMessage(plugin.addPrefix(ChatColor.RED + "That game is currently in progress, you can't join now!"));
					break;
				case RESETTING:
					player.sendMessage(plugin.addPrefix(ChatColor.RED + "That arena is resetting, please wait and try again!"));
					break;
				case READY:
				case STARTING:
					break;
				default:
					break;

				}
			}
		}

	}

}
