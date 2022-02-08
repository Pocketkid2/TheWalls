package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.Status;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

public class StartSubCommand extends TheWallsSubCommand {

	public StartSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return true;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("start");
	}

	@Override
	public String description() {
		return "Casts your vote to start the game you are currently in!";
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
		return "start";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Player player = (Player) sender;
		if (plugin.getGM().isInGame(player)) {
			Arena arena = plugin.getGM().getArenaForPlayer(player);
			if (arena.getStatus() != Status.READY) {
				player.sendMessage(plugin.addPrefix(ChatColor.RED + "You can only vote to start the game when the game is idle and waiting!"));
				return;
			}
			if (arena.hasVoted(player)) {
				player.sendMessage(plugin.addPrefix(ChatColor.RED + "You have already voted to start the game!"));
			} else {
				arena.castVote(player);
			}
		}
	}

}
